package com.example.babershopcomms

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.babershopcomms.database.CustomerResponse
import com.example.babershopcomms.database.adapter.customerAdapter
import com.example.babershopcomms.database.personItem
import com.google.firebase.auth.FirebaseAuth

class BarberAssignment : AppCompatActivity() {

    private lateinit var btnsignOut: Button
    private var layoutManager: RecyclerView.LayoutManager? = null
    private lateinit var display: RecyclerView

    companion object {
        fun getLaunchIntent(from: Context) = Intent(from, NewBarberAccount::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baber_assignment)

        display = findViewById(R.id.Lv_assignments)
        btnsignOut = findViewById(R.id.btn_signout)
        initializeUI(btnsignOut)



        val dataset = CustomerResponse(0 , mutableListOf<personItem>(
            personItem("Lennard Mazyck Jr ", "","11/20/20 @14:30"),
            personItem("Kate Wilson ", "","11/20/20 @16:30"),
            personItem("Darwin Mills ", "","11/21/20 @9:00"),

            ))
        val myAdapter = customerAdapter(
            dataset,
            ::createProfile)

        display.layoutManager = LinearLayoutManager(this)
        display.adapter = myAdapter

    }

    fun createProfile(item : personItem){
        Toast.makeText(this, "Called", Toast.LENGTH_SHORT).show()
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