package com.example.movieapitext

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_display_detail)
        val intentFromMainActivity = intent
        intentFromMainActivity.getParcelableExtra<MovieItem>(KEY_MOVIE_ITEM)?.let {
            //todo bind data with views
        }
    }
}