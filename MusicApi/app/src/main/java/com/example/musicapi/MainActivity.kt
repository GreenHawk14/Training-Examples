package com.example.musicapi

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), TabLayout.OnTabSelectedListener {

    var rockLocal: String = "Rock"
    var classicLocal: String = "Classic"
    var popLocal: String = "Pop"
    private lateinit var localApi: () -> Call<ResultResponse>

    lateinit var Music_display: RecyclerView

    private fun record(SongItem: SongItem)
    {

    }

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Music_display = findViewById(R.id.music_short_view)

        //Listens when the user clicks on the tab
        genre_tab.addOnTabSelectedListener(this)

        //allows user to scroll down to refresh the recycler.
        refresh_swiper.setOnRefreshListener {
            findMymusic(localApi)
        }

        localApi = MusicApi.initRetrofit()::findRockmusic
        findMymusic(localApi)
    }

    private fun openActivityDetails(music: SongItem)
    {
        //Intent needed to play each track.
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.setDataAndType(Uri.parse(music.previewUrl),"audio/mp3")
        startActivity(intent)

    }
    private fun findMymusic(Api: () -> Call<ResultResponse>)
    {
        //Calling MusicApi, if success lists details of each linked http links for library
        MusicApi.initRetrofit().findRockmusic().enqueue(
            object: Callback<ResultResponse> {

                override fun onResponse(
                    call: Call<ResultResponse>, response: Response<ResultResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Music_display.layoutManager=
                                GridLayoutManager(this@MainActivity,
                                    2)
                            Music_display.adapter =
                                MusicAdapter(it, ::openActivityDetails)
                        }
                    }
                }


                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        MusicApi.initRetrofit().findPopmusic().enqueue(
            object: Callback<ResultResponse> {

                override fun onResponse(
                    call: Call<ResultResponse>, response: Response<ResultResponse>) {
                    if (response.isSuccessful) {
                        response.body()?.let {
                            Music_display.layoutManager=
                                GridLayoutManager(this@MainActivity,
                                    2)
                            Music_display.adapter =
                                MusicAdapter(it, ::openActivityDetails)
                        }
                    }
                }


                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })


        MusicApi.initRetrofit().findClassicmusic().enqueue(
            object: Callback<ResultResponse> {

                override fun onResponse(
                    call: Call<ResultResponse>,
                    response: Response<ResultResponse>) {

                    Toast.makeText(this@MainActivity, "Success Call", Toast.LENGTH_SHORT).show()

                    if (response.isSuccessful) {
                        response.body()?.let {
                            Music_display.layoutManager=
                                GridLayoutManager(this@MainActivity,
                                    2)
                            Music_display.adapter =
                                MusicAdapter(it, ::openActivityDetails)
                        }
                    }
                }


                override fun onFailure(call: Call<ResultResponse>, t: Throwable) {
                    Toast.makeText(
                        this@MainActivity,
                        "Error",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

    }
    override fun onTabSelected(tab: TabLayout.Tab?)
    {
        // allows the applicatoin to communicate with the adapter.
            when(tab?.text)
            {
                rockLocal -> localApi = MusicApi.initRetrofit()::findRockmusic
                classicLocal -> localApi = MusicApi.initRetrofit()::findClassicmusic
                popLocal -> localApi = MusicApi.initRetrofit()::findPopmusic
            }
        Toast.makeText(this,"Calling server",Toast.LENGTH_SHORT).show()
        findMymusic(localApi)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }
}