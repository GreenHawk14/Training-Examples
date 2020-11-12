package com.example.recyclerleson

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.widget.TextView
import android.view.View

class CustomAdapter(val userList: ArrayList<User>): RecyclerView.Adapter<CustomAdapter.ViewHolder>()
{


    // method returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder
    {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    //method binding the data onto the list
    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        holder.bindItems(userList[position])
    }

    //method holding given size of the list
    override fun getItemCount(): Int {
        return userList.size
    }

    //class holding the list view
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    {
        fun bindItems(user: User)
        {
            val TextViewName = itemView.findViewById(R.id.textViewUsername) as TextView
            val TextViewAddress = itemView.findViewById(R.id.textViewAddress) as TextView
            TextViewName.text = user.name
            TextViewAddress.text = user.address
        }
    }
}