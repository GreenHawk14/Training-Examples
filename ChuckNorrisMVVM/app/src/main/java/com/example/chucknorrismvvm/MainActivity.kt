package com.example.chucknorrismvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.chucknorrismvvm.Model.NorrisRepository
import com.example.chucknorrismvvm.ViewModel.NorrisViewModel

class MainActivity : AppCompatActivity() {

    @Suppress("UNCHECKED_CAST")
    private val viewModel: NorrisViewModel by lazy {
        ViewModelProvider(this,
        object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
               return NorrisViewModel(repository) as T
            }

        }).get(NorrisViewModel::class.java)
    }

    private val repository: NorrisRepository by lazy{
        NorrisRepository()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //connect your view with the livedata
        viewModel.getJokeLiveData().observe(this,
                object : Observer<String> {

                    //Similarly to onNext from RxJava
                    //Get data when Observable changes
                    override fun onChanged(t: String?) {
                        //todo populate into Mainthread View
                    }

                })
    }
}