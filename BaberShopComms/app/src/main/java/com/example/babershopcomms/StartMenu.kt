package com.example.babershopcomms

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity



class StartMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_menu)

        val startBtn = findViewById<Button>(R.id.btn_StartLogin)
            startBtn.setOnClickListener {
                val startIntent = Intent(this, LoginScreen::class.java)
                startActivity(startIntent)
            }
    }
}