package com.example.kotlinstudy

import android.media.MediaPlayer
import android.os.Bundle
import android.provider.MediaStore.Audio.Media
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide

class MelonMusicPlayActivity : AppCompatActivity() {
    lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_melon_music_play)

        player = MediaPlayer()
        var title = findViewById<TextView>(R.id.melon_play_title)
        var thumbnail = findViewById<ImageView>(R.id.melon_play_thumbnail)

        title.text = intent.getStringExtra("MelonTitle")
        Glide.with(this).load(intent.getStringExtra("MelonThumbnail")).into(thumbnail)
        player.setDataSource(intent.getStringExtra("MelonSong"))
        player.start()
    }

    override fun onPause() {
        super.onPause()
        if (player.isPlaying) player.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        player.stop()
    }
}