package com.example.ditest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ditest.viewmodel.CustomViewModel
import com.example.ditest.viewmodel.CustomViewModelProvider
import javax.inject.Inject

class MainActivity : AppCompatActivity() {


    lateinit var viewmodel: CustomViewModel
    @Inject
    lateinit var customViewModelProvider: CustomViewModelProvider

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        CustomApplication.component.inject(this)
    }
}