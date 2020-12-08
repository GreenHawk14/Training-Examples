package com.example.babershopcomms

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "LoginScreen"
class LoginScreen : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

        val clientbtn = findViewById<Button>(R.id.btn_newClient)
            clientbtn.setOnClickListener {
                val clientintent = Intent(this, Customerphoneauth::class.java)
                //Toast.makeText(this, "New Client, Sweet! ", Toast.LENGTH_SHORT).show()
                Log.d(TAG, "Connecting to Client signUp...")
                startActivity(clientintent)
            }
        val barberbtn = findViewById<Button>(R.id.btn_Newbarber)
        barberbtn.setOnClickListener {
            val barberintent = Intent(this, NewBarberAccount::class.java)
            //Toast.makeText(this, "New Barber/Stylist, Welcome! ", Toast.LENGTH_SHORT).show()
            Log.d(TAG, "Connecting to Barber signUp...")
            startActivity(barberintent)
        }
    }
}
