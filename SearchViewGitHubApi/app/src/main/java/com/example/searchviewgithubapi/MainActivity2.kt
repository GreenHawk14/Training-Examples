package com.example.searchviewgithubapi

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main2.*
import retrofit2.Call
import retrofit2.Response

/*
    This user establish connection with the adapter. Populating the first recycler
    and displays in cardview a brief profile from the Github account pull from URL.
    Showing the Username, Avatar picture & Account ID.
 */

class MainActivity2 : AppCompatActivity(),
    androidx.appcompat.widget.SearchView.OnQueryTextListener{
   lateinit var adapter: SearchAdapter

    companion object{
        private const val SECOND_ACT_DATA = "SECOND_ACT_DATA"
        fun getSecondValues():String{
            return SECOND_ACT_DATA
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        Git_search.setOnQueryTextListener(this)


    }

    //Collecting the data for populating
    override fun onQueryTextSubmit(query: String?): Boolean {

        if(query==null || query.isEmpty())
        {
            return true
        }
        GitSearchApi.initRetrofit().getCollection(query).enqueue(
            object :  retrofit2.Callback<SearchResponse> {

                override fun onResponse(
                    call: Call<SearchResponse>, response: Response<SearchResponse>) {
                    if(response.isSuccessful)
                    {response.body()?.let {Git_result.layoutManager = LinearLayoutManager(this@MainActivity2)
                        Git_result.adapter = SearchAdapter(it, ::secondAct)
                        Toast.makeText(this@MainActivity2,"Updating Data ", Toast.LENGTH_SHORT).show()
                        }
                    }

                }

                override fun onFailure(call: Call<SearchResponse>, t: Throwable) {

                }

            }
        )
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {

        return false
    }
    private fun secondAct(user: Users)
    {
       Toast.makeText(this, "${user.login}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, UserBio::class.java)
        intent.putExtra(MainActivity2.SECOND_ACT_DATA,user)
        startActivity(intent)
    }

}