package com.example.kotlinstudy

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.zip.Inflater

class InstaFeedFragment : Fragment() {
    lateinit var retrofitService: RetrofitService

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insta_feed, container, false)
    }

    fun postLikeClick(post_id: Int) {
        retrofitService.postLikeClick(post_id).enqueue(object : Callback<Any> {
            override fun onResponse(p0: Call<Any>, p1: Response<Any>) {
                Toast.makeText(activity, "Like", Toast.LENGTH_LONG).show()
            }

            override fun onFailure(p0: Call<Any>, p1: Throwable) {
                Toast.makeText(activity, "Fail", Toast.LENGTH_LONG).show()
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        retrofitService = retrofit.create(RetrofitService::class.java)

        retrofitService.getInstaPosts().enqueue(object : Callback<ArrayList<InstaPost>> {
            override fun onResponse(
                p0: Call<ArrayList<InstaPost>>,
                p1: Response<ArrayList<InstaPost>>
            ) {
                if (p1.isSuccessful) {
                    val post = p1.body()
                    val feedRecyclerView =
                        view.findViewById<RecyclerView>(R.id.insta_feed_recyclerview)

                    feedRecyclerView.adapter = InstaFeedRecyclerViewAdapter(
                        LayoutInflater.from(activity),
                        post!!,
                        Glide.with(activity!!),
                        this@InstaFeedFragment,
                        activity as (InstaMainActivity)
                    )
                }
            }

            override fun onFailure(p0: Call<ArrayList<InstaPost>>, p1: Throwable) {
            }
        })
    }

    class InstaFeedRecyclerViewAdapter(
        val inflater: LayoutInflater,
        val postList: ArrayList<InstaPost>,
        val glide: RequestManager,
        val instaFeedFragment: InstaFeedFragment,
        val activity: InstaMainActivity
    ) :
        RecyclerView.Adapter<InstaFeedRecyclerViewAdapter.ViewHolder>() {
        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val profileImage: ImageView
            val userName: TextView
            val feedImage: ImageView
            val feedContent: TextView
            val feed_like_img: ImageView
            val feed_like_layer: ImageView

            init {
                profileImage = itemView.findViewById(R.id.insta_feed_profile_img)
                userName = itemView.findViewById(R.id.insta_feed_profile_username)
                feedImage = itemView.findViewById(R.id.insta_feed_img)
                feedContent = itemView.findViewById(R.id.insta_feed_content)
                feed_like_img = itemView.findViewById(R.id.insta_feed_like)
                feed_like_layer = itemView.findViewById(R.id.insta_feed_like_layer)

                feedImage.setOnClickListener {
                    instaFeedFragment.postLikeClick(postList.get(adapterPosition).id)
                    Thread {
                        activity.runOnUiThread {
                            feed_like_img.visibility = VISIBLE
                            feed_like_layer.visibility = VISIBLE
                        }
                        Thread.sleep(2000)
                        activity.runOnUiThread {
                            feed_like_img.visibility = INVISIBLE
                            feed_like_layer.visibility = INVISIBLE
                        }
                    }.start()
                }
            }
        }

        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {
            val view = inflater.inflate(R.layout.insta_feed_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(
            holder: InstaFeedRecyclerViewAdapter.ViewHolder,
            position: Int
        ) {
            postList.get(position).owner_profile.image.let {
                glide.load(it).centerCrop().into(holder.profileImage)
            }
            postList.get(position).image.let {
                glide.load(it).centerCrop().into(holder.feedImage)
            }
            holder.userName.text = postList.get(position).owner_profile.username
            holder.feedContent.text = postList.get(position).content
        }

        override fun getItemCount(): Int {
            return postList.size
        }
    }
}