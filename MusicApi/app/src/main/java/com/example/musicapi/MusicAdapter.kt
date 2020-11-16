package com.example.musicapi

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MusicAdapter(private val dataSet: ResultResponse, private val callback: (track: SongItem) -> Unit):
    RecyclerView.Adapter<MusicAdapter.ViewHolder>(){

    // Variables able to communicate with the view/items for the user.
    class ViewHolder( val musicItem: View):
        RecyclerView.ViewHolder(musicItem)
    {
        val artistName: TextView = musicItem.findViewById(R.id.tv_artistName)
        val collectionName: TextView = musicItem.findViewById(R.id.tv_CollectionName)
        val artworkUrl60: ImageView = musicItem.findViewById(R.id.iv_artwork)
        val trackPrice: TextView = musicItem.findViewById(R.id.track_price)

        fun onBind(song: SongItem, callback: (track: SongItem) -> Unit) {
                musicItem.setOnClickListener {
                    callback.invoke(song)
                }
            Picasso.get().load(song.artworkUrl60).into(artworkUrl60)
            artistName.text= song.artistName
            collectionName.text = song.collectionName
            trackPrice.text = song.trackPrice.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater
                .from(parent.context).
                inflate(R.layout.item_display,parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(this.dataSet.results[position],callback)
    }

    override fun getItemCount(): Int {
        return dataSet.results.size
    }

}
