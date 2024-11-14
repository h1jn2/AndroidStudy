package com.example.kotlinstudy

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ThreadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)

//        val currentThread = Thread.currentThread()
//        Log.d("dataa", "" + currentThread)

//        Thread {
//            for (i in 1..1000) {
//                Log.d("dataa", "a" + i)
//            }
//        }.start()

//        Thread {
//            for (i in 1..1000) {
//                Log.d("dataa", "b" + i)
//            }
//        }.start()
        Thread {
            val currentThread = Thread.currentThread()
            Log.d("dataa", "" + currentThread)
//            findViewById<TextView>(R.id.test_text).text = "changed"

            runOnUiThread {
                findViewById<TextView>(R.id.test_text).text = "changed"
            }
            // UI 관련 작업을 메인 Thread 에서 하지 않을 경우 Main Thread 의 Queue로 들어감
            // -> 에러가 발생하지 않을 수도 있음
        }.start()
    }
}