package com.example.serviceapplication

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.example.serviceapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
        val music = MediaPlayer.create(
            this, R.raw.embrace);

        binding.startButton.setOnClickListener {
            music.start()
            startService(Intent(this,SongService::class.java))
        }
        binding.stopButton.setOnClickListener {
            music.stop()
            stopService(Intent(this,SongService::class.java))

        }


    }
}