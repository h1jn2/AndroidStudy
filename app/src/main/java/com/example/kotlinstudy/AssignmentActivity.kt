package com.example.kotlinstudy

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AssignmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assignment)

        var personList = mutableListOf<Person>()
        val container = findViewById<LinearLayoutCompat>(R.id.assignment_container)
        val inflater = LayoutInflater.from(this@AssignmentActivity)

        for (i in 0..30) {
            if (i < 10) personList.add(Person("" + i + "번째 사람", "010-1111-110" + i))
            else personList.add(Person("" + i + "번째 사람", "010-1111-11" + i))
        }
        personList.forEach {
            val item = inflater.inflate(R.layout.assignment_item, null)
            val nthImage = item.findViewById<ImageView>(R.id.assignment_image)
            val nthPerson = item.findViewById<TextView>(R.id.nth_person)
            val nthPhone = item.findViewById<TextView>(R.id.nth_phone)

            nthImage.setImageDrawable(resources.getDrawable(R.drawable.logo_kt, this.theme))
            nthPerson.text = it.nthPerson
            nthPhone.text = it.nthPhone

            container.addView(item)

            item.setOnClickListener {
                val intent = Intent(this, Assignment2Activity::class.java)
                intent.putExtra("nthPerson", nthPerson.text)
                intent.putExtra("nthPhone", nthPhone.text)
                startActivity(intent)
            }
        }
    }
}

class Person(val nthPerson: String, val nthPhone: String)