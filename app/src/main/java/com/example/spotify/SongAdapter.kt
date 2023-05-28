package com.example.spotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SongAdapter(private val data: List<PlaylistResponse.Playlist.Song>, private val listener: OnSongClickListener)
    : RecyclerView.Adapter<SongAdapter.ViewHolder>() {

    interface OnSongClickListener {
        fun onSongClick(song: PlaylistResponse.Playlist.Song)
        fun onFavoriteClick(song: PlaylistResponse.Playlist.Song)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tv_songName)
        val artist: TextView = view.findViewById(R.id.tv_artistName)
        val favorite: ImageView = view.findViewById(R.id.iv_favorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_song, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val song = data[position]
        holder.name.text = song.name
        holder.artist.text = song.artist

        holder.itemView.setOnClickListener {
            listener.onSongClick(song)
        }

        holder.favorite.setOnClickListener {
            it.isSelected = !it.isSelected
            listener.onFavoriteClick(song)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
