package com.example.kotlinstudy

import android.media.MediaParser.InputReader
import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class NetworkActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_network)

        NetworkAsyncTask().execute()

    }
}

// 메인 쓰레드에서 네트워크 작업 시 오류 발생 -> 쓰레드를 따로 만들어서 작업
// 메인 쓰레드에서는 UI 관련 작업만
class NetworkAsyncTask(): AsyncTask<Any?, Any?, Any?>() {
    override fun doInBackground(vararg params: Any?): Any? {
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")   // Header

        var buffer = ""
        if (connection.responseCode == HttpURLConnection.HTTP_OK) {
            val reader = BufferedReader(
                InputStreamReader(
                    connection.inputStream,
                    "UTF-8"
                )
            )
            buffer = reader.readLine()
            Log.d("testt", buffer)
        }
        return null
    }
}