package com.example.movieapitext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
const val KEY_MOVIE_ITEM: String = "MainActivity_KEY_MOVIE_ITEM"
class MainActivity : AppCompatActivity() {


    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerview = findViewById(R.id.recycler_view_movie)
        getMovies()
    }

    private fun getMovies(){

        MoviesApi.initRetrofit().getMovies().enqueue(
            object : Callback<List<MovieItem>> {

                override fun onResponse(
                    call: Call<List<MovieItem>>,
                    response: Response<List<MovieItem>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            recyclerview.layoutManager=
                                    GridLayoutManager(this@MainActivity,
                                    2)
                            recyclerview.adapter =
                                    MovieAdapter(it,
                                    ::openActivityDetails)
                        }
                    }
                }

                override fun onFailure(call: Call<List<MovieItem>>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        )
    }
    private fun openActivityDetails(movieItem: MovieItem)
    {
        val intent = Intent()
        intent.setClass(this,DetailActivity::class.java)
        intent.putExtra(KEY_MOVIE_ITEM, movieItem)
        startActivity(intent)
    }
}