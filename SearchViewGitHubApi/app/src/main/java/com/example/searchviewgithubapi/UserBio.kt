package com.example.searchviewgithubapi

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_bio.*
import retrofit2.Call
import retrofit2.Response
import com.example.searchviewgithubapi.Users as SearchResponse

/*
 This is the second activity where we display the information collected from User data class
 through using Intent

 */
class UserBio : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_bio)
        val intent =getIntent()// gather the data
        var Ank = intent.getParcelableExtra<SearchResponse>(MainActivity2.getSecondValues())

        if(Ank != null)
        {
            val query = Ank.login
            if (query != null) {
                GitSearchApi.initRetrofit().getCollection(query).enqueue(
                    object :  retrofit2.Callback<com.example.searchviewgithubapi.SearchResponse> {

                        override fun onResponse(
                            call : Call<com.example.searchviewgithubapi.SearchResponse>,
                            response : Response<com.example.searchviewgithubapi.SearchResponse>,
                        ) {
                            if(response.isSuccessful) {
                                response.body()?.let {
                                    txtUser.text = Ank.login ?: "N/A"
                                    txtScore.text = Ank.score
                                    txt_JoinDate.text = Ank.created_at ?: "N/A"
                                    User_bio.text = Ank.bio ?: "N/A"
                                    txt_location.text = Ank.location ?: "N/A"
                                    txt_followers.text = Ank.followers.toString()
                                    txt_following.text = Ank.following.toString()
                                    Picasso.get().load(Ank.avatar_url).into(im_profile)

                                }
                            }

                        }

                        override fun onFailure(call: Call<com.example.searchviewgithubapi.SearchResponse>, t: Throwable) {
                            println("No data to parse")
                        }


            })
        }
    }
}}