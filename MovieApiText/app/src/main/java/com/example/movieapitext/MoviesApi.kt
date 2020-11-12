package com.example.movieapitext

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

//https://api.androidhive.info/json/movies.json
interface MoviesApi {

    @GET("json/movies.json")
    fun getMovies(): Call<List<MovieItem>>

    companion object{

        fun initRetrofit(): MoviesApi{

            return Retrofit.Builder()
                .baseUrl("https://api.androidhive.info/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MoviesApi::class.java)
        }
    }
}

//implement retrofit
// 1 library dependencies
// 2 interface
// 3 create retrofit object
// 4 Enqueue the process...