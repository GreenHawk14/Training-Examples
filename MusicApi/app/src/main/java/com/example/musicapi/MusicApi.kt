package com.example.musicapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface MusicApi {

    //Commutes the information needed to pass through the application

        @GET("https://itunes.apple.com/search?term=rock&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
        fun findRockmusic(): Call<ResultResponse>
        //Rock Listing

        @GET(" https://itunes.apple.com/search?term=pop&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
        fun findPopmusic(): Call<ResultResponse>
        //Pop Music Listing

        @GET("https://itunes.apple.com/search?term=classick&amp;amp;media=music&amp;amp;entity=song&amp;amp;limit=50")
        fun findClassicmusic(): Call<ResultResponse>
        //Classic Music Listing


        companion object {

            fun initRetrofit(): MusicApi{
                return Retrofit.Builder()
                        .baseUrl("https://itunes.apple.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build().create(MusicApi::class.java)
            }

        }
}