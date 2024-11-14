package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class FragmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment)

        val fragmentManager = supportFragmentManager
        val fragmentOne = FragmentOne()

        (findViewById<TextView>(R.id.add)).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            val bundle = Bundle()
            bundle.putString("key", "hello")
            fragmentOne.arguments = bundle

            transaction.replace(R.id.root, fragmentOne, "fragment_one_tag")
            transaction.commit()
        }
        (findViewById<TextView>(R.id.remove)).setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            transaction.remove(fragmentOne)
            transaction.commit()
        }
        (findViewById<TextView>(R.id.access_fragment)).setOnClickListener {
//            val fragmentOne = supportFragmentManager.findFragmentById(R.id.fragment_one) as FragmentOne
            val fragmentOne = supportFragmentManager.findFragmentByTag("fragment_one_tag") as FragmentOne
            fragmentOne.printTestLog()
        }
    }

    fun printTestLog() {
        Log.d("dataa", "printTestLog()")
    }
}