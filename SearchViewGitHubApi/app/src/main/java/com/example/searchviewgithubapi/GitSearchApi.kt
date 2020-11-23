package com.example.searchviewgithubapi

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
/*
    This is the network query, Showing where we gather the information to use.
 */
interface GitSearchApi
{

    @GET("search/users")
    //multiple users
    fun getCollection(
        @Query("q") userName:String
    ): Call<SearchResponse>

    fun getSingleUser(
        @Path("users") userName : String
    ): Call<SearchResponse>

    companion object{

        fun initRetrofit(): GitSearchApi{

            return Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GitSearchApi::class.java)
        }
    }
}