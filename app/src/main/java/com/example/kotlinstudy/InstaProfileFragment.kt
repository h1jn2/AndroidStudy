package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaProfileFragment : Fragment() {
    lateinit var userProfileImage: ImageView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_insta_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        userProfileImage = view.findViewById(R.id.insta_profile_img)

        view.findViewById<TextView>(R.id.insta_profile_button).setOnClickListener {
            val intent =
                Intent(activity as InstaMainActivity, InstaProfileChageActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        val header = HashMap<String, String>()
        val sharedPreference =
            (activity as InstaMainActivity).getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val token = sharedPreference.getString("token", "")
        header.put("Authorization", "token " + token!!)

        val glide = Glide.with(activity as InstaMainActivity)

        retrofitService.getUserInfo(header).enqueue(object : Callback<UserInfo> {
            override fun onResponse(p0: Call<UserInfo>, p1: Response<UserInfo>) {
                if (p1.isSuccessful) {
                    val userInfo = p1.body()!!
                    userInfo.profile.image?.let {
                        glide.load(it).into(userProfileImage)
                    }
                }
            }

            override fun onFailure(p0: Call<UserInfo>, p1: Throwable) {
            }
        })
    }
}
