package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.doAfterTextChanged
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class InstaJoinActivity : AppCompatActivity() {
    var username: String = ""
    var password1: String = ""
    var password2: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_join)

        val retrofit = Retrofit.Builder().baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val retrofitService = retrofit.create(RetrofitService::class.java)
        val user = HashMap<String, Any>()

        findViewById<EditText>(R.id.insta_join_edit_id).doAfterTextChanged {
            username = it.toString()
        }
        findViewById<EditText>(R.id.insta_join_edit_pw).doAfterTextChanged {
            password1 = it.toString()
        }
        findViewById<EditText>(R.id.insta_join_edit_check_pw).doAfterTextChanged {
            password2 = it.toString()
        }



        findViewById<TextView>(R.id.insta_join_login_button).setOnClickListener {
            user.put("username", username)
            user.put("password1", password1)
            user.put("password2", password2)
            retrofitService.createInstaUser(user).enqueue(object : Callback<User> {
                override fun onResponse(p0: Call<User>, p1: Response<User>) {
                    if (p1.isSuccessful) {
                        val userToken = p1.body()!!
                        Log.d("tokennn", userToken.token)
                        val sharedPreferenced = getSharedPreferences("user_info", Context.MODE_PRIVATE)
                        val editor: SharedPreferences.Editor = sharedPreferenced.edit()
                        editor.putString("token", userToken.token)
                        editor.putString("user_id", userToken.id.toString())
                        editor.commit()
                    }

                }

                override fun onFailure(p0: Call<User>, p1: Throwable) {
                }
            })
            startActivity(Intent(this, InstaLoginActivity::class.java))
        }
    }
}