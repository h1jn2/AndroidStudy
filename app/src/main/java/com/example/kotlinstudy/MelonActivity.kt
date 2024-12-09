package com.example.kotlinstudy

import android.content.Intent
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MelonActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon)

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)
        val playButton: ImageView = findViewById(R.id.melon_play_button)
        val pauseButton: ImageView = findViewById(R.id.melon_pause_button)
        val beforeButton: ImageView = findViewById(R.id.melon_before_button)
        val nextButton: ImageView = findViewById(R.id.melon_next_button)
        val player: MediaPlayer = MediaPlayer()



        retrofitService.getMelonList().enqueue(object : Callback<ArrayList<MelonFromServer>> {
            override fun onResponse(
                p0: Call<ArrayList<MelonFromServer>>,
                p1: Response<ArrayList<MelonFromServer>>
            ) {
                if (p1.isSuccessful) {
                    val melon = p1.body()
                    val glide = Glide.with(this@MelonActivity)

                    findViewById<RecyclerView>(R.id.melon_recyclerview).apply {
                        this.adapter = MelonRecyclerViewHolder(
                            melon!!,
                            LayoutInflater.from(this@MelonActivity),
                            glide
                        )
                        this.layoutManager = LinearLayoutManager(this@MelonActivity)
                    }
                    playButton.setOnClickListener {
                        playButton.visibility = View.GONE
                        pauseButton.visibility = View.VISIBLE
                        if (!player.isPlaying) {
                            player.setDataSource(melon!!.get(0).song)
                            player.start()
                        }
                    }
                    playButton.setOnClickListener {
                        playButton.visibility = View.VISIBLE
                        pauseButton.visibility = View.GONE
                        if (player.isPlaying) {
                            player.stop()
                        }
                    }
                }
            }

            override fun onFailure(p0: Call<ArrayList<MelonFromServer>>, p1: Throwable) {
            }
        })


    }
}

class MelonRecyclerViewHolder(
    val melonList: ArrayList<MelonFromServer>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<MelonRecyclerViewHolder.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
        val thumbnail: ImageView

        init {
            title = itemView.findViewById(R.id.melon_item_title)
            thumbnail = itemView.findViewById(R.id.melon_item_thumbnail)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, MelonMusicPlayActivity::class.java)
                val position: Int = adapterPosition

                intent.putExtra("MelonTitle", melonList.get(position).title)
                intent.putExtra("MelonSong", melonList.get(position).song)
                intent.putExtra("MelonThumbnail", melonList.get(position).thumbnail)
                itemView.context.startActivity(intent)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.melon_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = melonList.get(position).title
        glide.load(Uri.parse(melonList.get(position).thumbnail)).into(holder.thumbnail)
    }

    override fun getItemCount(): Int {
        return melonList.size
    }
}