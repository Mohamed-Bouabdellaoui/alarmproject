package com.example.alarmproject

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val hourEdit: EditText = findViewById(R.id.hourEditText)
        val minuteEdit: EditText = findViewById(R.id.minuteEditText)
        val button: Button = findViewById(R.id.button)

        button.setOnClickListener {
            val hourStr = hourEdit.text.toString()
            val minuteStr = minuteEdit.text.toString()

            if (hourStr.isNotEmpty() && minuteStr.isNotEmpty()) {
                try {
                    val hours = hourStr.toInt()
                    val minutes = minuteStr.toInt()

                    // Validate input
                    if (hours < 0 || minutes < 0 || minutes >= 60) {
                        Toast.makeText(
                            applicationContext,
                            "Please enter valid hours and minutes (minutes should be 0-59)",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@setOnClickListener
                    }

                    // Convert hours and minutes to milliseconds
                    val totalMillis = (hours * 60 * 60 * 1000L) + (minutes * 60 * 1000L)

                    val i = Intent(applicationContext, MyBroadcastReceiver::class.java)
                    i.action = "com.example.MY_ALARM_ACTION"
                    val pi = PendingIntent.getBroadcast(
                        applicationContext,
                        111,
                        i,
                        PendingIntent.FLAG_IMMUTABLE
                    )

                    val am: AlarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    am.set(
                        AlarmManager.RTC_WAKEUP,
                        System.currentTimeMillis() + totalMillis,
                        pi
                    )

                    val message = when {
                        hours > 0 && minutes > 0 -> "$hours hours and $minutes minutes"
                        hours > 0 -> "$hours hours"
                        else -> "$minutes minutes"
                    }

                    Toast.makeText(
                        applicationContext,
                        "Alarm is set for $message from now",
                        Toast.LENGTH_LONG
                    ).show()

                } catch (e: NumberFormatException) {
                    Toast.makeText(
                        applicationContext,
                        "Please enter valid numbers",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    "Please enter both hours and minutes",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}