package com.example.searchviewgithubapi

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProfileAdapter(private var Profile: List<Users>):
        RecyclerView.Adapter<ProfileAdapter.ProfileViewHolder>(){

    class ProfileViewHolder (val ProfileView: View):
    RecyclerView.ViewHolder(ProfileView)
    {
        val Avatar_img: ImageView = ProfileView.findViewById(R.id.im_profile)
        val Username: TextView = ProfileView.findViewById(R.id.txtUser)
        val Rating: TextView = ProfileView.findViewById(R.id.txtScore)
        val Locale: TextView = ProfileView.findViewById(R.id.txt_location)
        val StartDate: TextView = ProfileView.findViewById(R.id.txt_JoinDate)
        val followerBase: TextView = ProfileView.findViewById(R.id.txt_followers)
        val followingBase: TextView = ProfileView.findViewById(R.id.txt_following)
        val profileBio: TextView = ProfileView.findViewById(R.id.User_bio)


        fun onBind(profile_account: Users)
        {
//            ProfileView.setOnClickListener {
//                profileCallback.invoke(profile_account)
//            }
            Picasso.get().load(profile_account.avatar_url).into(Avatar_img)
            Username.text = profile_account.login ?: "N/A"
            Rating.text = profile_account.score ?: "N/A"
            Locale.text = profile_account.location ?: "N/A"
            StartDate.text = profile_account.created_at ?: "N/A"
            profileBio.text = profile_account.bio ?: "N/A"


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_user_bio,parent, false))
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.onBind(this.Profile[position])
    }

    override fun getItemCount() = Profile.size

    fun updateDataSet(items : Collection<Users>) {
        this.Profile = Profile
        notifyDataSetChanged()
    }
}