package com.example.babershopcomms

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

private const val TAG = "NewClientUser"
class NewClientUser : AppCompatActivity() {

    private lateinit var name: EditText
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var phoneNum: EditText
    private lateinit var email: EditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_client_user)

        name = findViewById(R.id.txtName)
        username = findViewById(R.id.txtUsername)
        password = findViewById(R.id.txtPasscode)
        phoneNum = findViewById(R.id.txtPhone)
        email = findViewById(R.id.txtEmail)


    }
}