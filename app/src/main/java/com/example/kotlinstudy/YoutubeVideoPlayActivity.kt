package com.example.kotlinstudy

import android.net.Uri
import android.os.Bundle
import android.widget.VideoView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class YoutubeVideoPlayActivity : AppCompatActivity() {
    private lateinit var videoView: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_youtube_video_play)

        videoView = findViewById(R.id.youtube_video_view)
        val videoUrl = intent.getStringExtra("video")

        videoView.setVideoURI(Uri.parse(videoUrl))
        videoView.setOnPreparedListener {
            videoView.start()
        }
    }

    override fun onPause() {
        super.onPause()
        if (videoView.isPlaying) videoView.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        videoView.stopPlayback()
    }
}