package com.example.kotlinstudy

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SharedPreferenceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shared_preference)

        findViewById<TextView>(R.id.create).setOnClickListener {
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key", "hello")
            editor.commit()
        }
        findViewById<TextView>(R.id.read).setOnClickListener {
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val value1 = sharedPreferences.getString("key", "wrong")
            Log.d("testt", value1!!)
        }
        findViewById<TextView>(R.id.update).setOnClickListener {
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("key", "hellooo")
            editor.commit()
        }
        findViewById<TextView>(R.id.delete).setOnClickListener {
            val sharedPreferences = getSharedPreferences("table_name", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.clear()
            editor.commit()
        }
    }
}