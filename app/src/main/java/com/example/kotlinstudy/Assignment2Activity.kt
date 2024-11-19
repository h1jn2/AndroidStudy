package com.example.kotlinstudy

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Assignment2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment2)

        val nthPerson = findViewById<TextView>(R.id.assign_nth_person)
        val nthPhone = findViewById<TextView>(R.id.assign_nth_phone)

        nthPerson.text = intent.getStringExtra("nthPerson")
        nthPhone.text = intent.getStringExtra("nthPhone")
    }
}