package com.maharramg.musicalquiz

import android.media.MediaPlayer
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.squareup.picasso.Picasso

class Details : AppCompatActivity() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_details)

        val actionBar = supportActionBar
        supportActionBar?.title = "Details"
        actionBar?.setDisplayShowHomeEnabled(true)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Get data from the intent
        val songTitle = intent.getStringExtra("SONG_TITLE")
        val songArtist = intent.getStringExtra("SONG_ARTIST")
        val songImageUrl = intent.getStringExtra("SONG_IMAGE_URL")
        val songPreviewUrl = intent.getStringExtra("SONG_PREVIEW_URL")

        // Set data to the UI components (TextViews, ImageViews)
        val titleTextView: TextView = findViewById(R.id.songTitle)
        val artistTextView: TextView = findViewById(R.id.songArtist)
        val songImageView: ImageView = findViewById(R.id.songImage)
        val playButton: ImageButton = findViewById(R.id.btnPlay)
        val pauseButton: ImageButton = findViewById(R.id.btnPause)

        titleTextView.text = songTitle
        artistTextView.text = songArtist
        Picasso.get().load(songImageUrl).into(songImageView)

        songPreviewUrl?.let { previewUrl ->
            mediaPlayer = MediaPlayer.create(this, previewUrl.toUri())
        }

        playButton.setOnClickListener {
            mediaPlayer?.start()
        }

        pauseButton.setOnClickListener {
            mediaPlayer?.pause()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mediaPlayer?.release()
        mediaPlayer = null
    }
}