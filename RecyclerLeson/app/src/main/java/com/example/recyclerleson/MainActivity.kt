package com.example.recyclerleson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.LinearLayout.VERTICAL
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Get recycler from xml file
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView_pan)

        //add a LayoutManager to recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL,false)

        //create an arrayList to store user using the data class user
        val users = ArrayList<User>()

        //add dummy values to populate list
        users.add(User("Belal Khan", "Ranchi Jharkhand"))
        users.add(User("Ramiz Khan", "Ranchi Jharkhand"))
        users.add(User("Faiz Khan", "Ranchi Jharkhand"))
        users.add(User("Yashar Khan", "Ranchi Jharkhand"))

        //create the adapter
        val adapter = CustomAdapter(users)

        //add adapter to recyclerView
        recyclerView.adapter = adapter
    }
}