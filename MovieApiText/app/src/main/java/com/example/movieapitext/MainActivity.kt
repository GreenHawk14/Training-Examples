package com.example.movieapitext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivity"
const val KEY_MOVIE_ITEM: String = "MainActivity_KEY_MOVIE_ITEM"
class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                            //todo update the RV
                            Log.d(TAG, "onResponse: ${it.size}")
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