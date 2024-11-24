package com.example.alarmproject

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AlarmOnActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_alarm_on)

        val mediaPlayer = MediaPlayer.create(applicationContext, R.raw.alarmtone)
        mediaPlayer.start()
        val button2: Button = findViewById(R.id.button2)
        button2.setOnClickListener {
            mediaPlayer.stop()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()

        }


    }
}