package com.example.movieapitext

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter(val dataSet: List<MovieItem>,
val activityCallback: (movie: MovieItem) -> Unit): RecyclerView.Adapter<MovieAdapter.MovieViewHolder>()
{
    class MovieViewHolder(val movieView: View): RecyclerView.ViewHolder(movieView)
    {
        private val posterImage: ImageView =
                movieView.findViewById(R.id.iv_item_movie_poster)

        fun onBind(movieItem: MovieItem,
            openDetailCallback: (movieItem: MovieItem) -> Unit)
        {
            movieView.setOnClickListener{
                openDetailCallback.invoke(movieItem)
            }
            Picasso.get().load(movieItem.image).into(posterImage)
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout,
                        parent, false)
        )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.onBind(dataSet[position], activityCallback)
    }
}