package com.example.searchviewgithubapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlin.reflect.KFunction2

/*
This is the first adapter linking the first recycler. Pulling only the Avatar picture,
Username, and Account ID.
 */

class SearchAdapter(private var Collect: SearchResponse, private val callback: (tracker: Users)->Unit):
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()
{

    class SearchViewHolder(val SearchView: View):
        RecyclerView.ViewHolder(SearchView) {

        val Profile_img: ImageView = SearchView.findViewById(R.id.imageView)
        val User_name: TextView = SearchView.findViewById(R.id.txtName)



        fun onBind(profile: Users, callback: (tracker: Users) -> Unit) {
            SearchView.setOnClickListener {
                callback.invoke(profile)
            }
            User_name.text= profile.login
            Picasso.get().load(profile.avatar_url).into(Profile_img)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        return SearchViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.display_profile,parent,false))
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        holder.onBind(Collect.items[position],callback)
    }

    override fun getItemCount() :Int
    {
        return Collect.items.count()
    }

}
