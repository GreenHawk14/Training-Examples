package com.example.babershopcomms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class BarberAssignment : AppCompatActivity() {

    private lateinit var btnsignOut: Button

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, NewBarberAccount::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baber_assignment)

        btnsignOut = findViewById(R.id.btn_signout)
        initializeUI(btnsignOut)

    }

    private fun initializeUI(btnsignOut: Button) {
        btnsignOut.setOnClickListener {
            logout()
        }
    }

    private fun logout() {

        val intentMove = Intent(this, LoginScreen::class.java)
        startActivity(intentMove)
        FirebaseAuth.getInstance().signOut()
    }

}