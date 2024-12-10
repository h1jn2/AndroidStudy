package com.example.kotlinstudy

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class InstaSplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insta_splash)

        val sharedPreferenced = getSharedPreferences("user_info", Context.MODE_PRIVATE)
        val token = sharedPreferenced.getString("token", "empty")

        when (token) {
            "empty" -> {
                startActivity(Intent (this, InstaLoginActivity::class.java))
            }
            else -> {
                startActivity(Intent (this, InstaMainActivity::class.java))
            }
        }
    }
}