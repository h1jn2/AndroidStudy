package com.example.kotlinstudy

import android.os.AsyncTask
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class AsyncActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_async)

        (findViewById<TextView>(R.id.start)).setOnClickListener {
            BackgroundAsyncTask (
                findViewById(R.id.progressbar),
                findViewById(R.id.progressPer)
            ).execute()
        }
    }
}

class BackgroundAsyncTask(val progressBar: ProgressBar, val progressPer: TextView): AsyncTask<Int, Int, Int>() {
    // params, progress, result
    // params -> doInBackground에서 사용
    // progress -> onProgressUpdate에서 사용
    // result -> onPostExcute에서 사용

    var percent: Int = 0
    override fun doInBackground(vararg params: Int?): Int {
        while (isCancelled() == false) {
            percent++
            if (percent == 100) break
            else {
                publishProgress(percent)
            }
        }
        return percent
    }

    override fun onPreExecute() {
        percent = 0
        progressBar.setProgress(percent)
    }

    override fun onPostExecute(result: Int?) {
        progressPer.text = "작업이 완료되었습니다."
    }

    override fun onProgressUpdate(vararg values: Int?) {
        progressBar.setProgress(values[0] ?: 0)
        progressPer.text = "퍼센트: "+ values[0]
    }

    override fun onCancelled() {
        percent = 0
        progressBar.setProgress(percent)
        progressPer.text = "작업이 취소되었습니다."
    }



}