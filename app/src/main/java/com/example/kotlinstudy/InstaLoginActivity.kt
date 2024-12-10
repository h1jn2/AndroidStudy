package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaLoginActivity : AppCompatActivity() {
    var username: String = ""
    var password: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_login)

        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitService = retrofit.create(RetrofitService::class.java)

        findViewById<EditText>(R.id.insta_join_edit_id).doAfterTextChanged {
            username = it.toString()
        }
        findViewById<EditText>(R.id.insta_join_edit_pw).doAfterTextChanged {
            password = it.toString()
        }
        findViewById<TextView>(R.id.insta_join_join_button).setOnClickListener {
            startActivity(Intent(this, InstaJoinActivity::class.java))
        }
        findViewById<TextView>(R.id.insta_join_login_button).setOnClickListener {
            val user = HashMap<String, Any>()
            user.put("username", username)
            user.put("password", password)
            retrofitService.loginInstaUser(user).enqueue(object: Callback<User> {
                override fun onResponse(p0: Call<User>, p1: Response<User>) {
                    if (p1.isSuccessful) {
                        val userToken: User = p1.body()!!
                        Log.d("tokennn", userToken.token)
                        val sharedPreferenced = getSharedPreferences("user_info", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferenced.edit()
                        editor.putString("token", userToken.token)
                        editor.putString("user_id", userToken.id.toString())
                        editor.commit()
                        startActivity(Intent(this@InstaLoginActivity, InstaMainActivity::class.java))
                    }
                    else {
                        Log.d("tokennn", p1.code().toString())
                    }
                }

                override fun onFailure(p0: Call<User>, p1: Throwable) {
                }
            })
        }


    }
}