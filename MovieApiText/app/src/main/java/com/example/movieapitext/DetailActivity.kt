package com.example.movieapitext

import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_display_detail)
        val intentFromMainActivity = intent
        intentFromMainActivity.getParcelableExtra<MovieItem>(KEY_MOVIE_ITEM)?.let {
            findViewById<TextView>(R.id.movie_title).text = it.title
            findViewById<TextView>(R.id.movie_release_year).text = it.releaseYear.toString()
            findViewById<TextView>(R.id.movie_genre).text = it.genre.toString()
            findViewById<ProgressBar>(R.id.rating_movie).progress = it.rating.toInt()
            findViewById<ImageView>(R.id.iv_movie_poster).showPoster(it.image)
        }
    }
}