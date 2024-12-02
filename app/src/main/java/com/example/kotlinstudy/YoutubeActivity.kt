package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import okhttp3.Request
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URLEncoder

class YoutubeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube)


        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getYoutubeList().enqueue(object : Callback<ArrayList<YoutubeFromServer>> {
            override fun onResponse(
                p0: Call<ArrayList<YoutubeFromServer>>,
                p1: Response<ArrayList<YoutubeFromServer>>
            ) {
                if (p1.isSuccessful) {
                    val youtube = p1.body()
                    val glide = Glide.with(this@YoutubeActivity)
                    findViewById<RecyclerView>(R.id.youtube_recyclerview).apply {
                        this.adapter = YoutubeRecyclerViewHolder(
                            youtube!!,
                            LayoutInflater.from(this@YoutubeActivity),
                            glide
                        )
                        this.layoutManager = LinearLayoutManager(this@YoutubeActivity)
                    }
                }
            }

            override fun onFailure(p0: Call<ArrayList<YoutubeFromServer>>, p1: Throwable) {
            }
        })
    }
}

class YoutubeRecyclerViewHolder(
    val youtubeList: ArrayList<YoutubeFromServer>,
    val inflater: LayoutInflater,
    val glide: RequestManager
) : RecyclerView.Adapter<YoutubeRecyclerViewHolder.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val thumbnail: ImageView
        val title: TextView
        val content: TextView

        init {
            thumbnail = itemView.findViewById(R.id.youtube_thumbnail)
            title = itemView.findViewById(R.id.youtube_title)
            content = itemView.findViewById(R.id.youtube_content)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, YoutubeVideoPlayActivity::class.java)
                val position: Int = adapterPosition
                intent.putExtra("video", youtubeList.get(position).video)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.youtube_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        glide.load(youtubeList.get(position).thumbnail)
            .into(holder.thumbnail)
        holder.title.text = youtubeList.get(position).title
        holder.content.text = youtubeList.get(position).content
    }

    override fun getItemCount(): Int {
        return youtubeList.size
    }
}