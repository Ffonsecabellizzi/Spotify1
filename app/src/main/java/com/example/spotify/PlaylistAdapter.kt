package com.example.spotify

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PlaylistAdapter(private val listener: OnPlaylistClickListener) : RecyclerView.Adapter<PlaylistAdapter.PlaylistViewHolder>() {
    private var playlists = emptyList<PlaylistResponse.Playlist>()

    interface OnPlaylistClickListener {
        fun onPlaylistClick(playlist: PlaylistResponse.Playlist)
    }

    inner class PlaylistViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        init {
            itemView.setOnClickListener(this)
        }

        fun bind(playlist: PlaylistResponse.Playlist) {
            val imageView = itemView.findViewById<ImageView>(R.id.playlist_image)
            Glide.with(itemView.context)
                .load(playlist.dummyImageUrl)
                .into(imageView)

            itemView.findViewById<TextView>(R.id.playlist_name).text = playlist.nameOfSong
            itemView.findViewById<TextView>(R.id.playlist_followers).text = playlist.numberOfFollowers.toString()
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                val playlist = playlists[position]
                listener.onPlaylistClick(playlist)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_playlist, parent, false)
        return PlaylistViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = playlists[position]
        holder.bind(playlist)
    }

    override fun getItemCount(): Int = playlists.size

    fun submitList(playlists: List<PlaylistResponse.Playlist>) {
        this.playlists = playlists
        notifyDataSetChanged()
    }
}


/*
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.spotify.PlaylistResponse
import com.example.spotify.databinding.ItemPlaylistBinding
import com.squareup.picasso.Picasso

class PlaylistAdapter : ListAdapter<PlaylistResponse.Playlist, PlaylistAdapter.PlaylistViewHolder>(PlaylistDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaylistViewHolder {
        return PlaylistViewHolder(ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PlaylistViewHolder, position: Int) {
        val playlist = getItem(position)
        holder.bind(playlist)
    }

    class PlaylistViewHolder(private val binding: ItemPlaylistBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(playlist: PlaylistResponse.Playlist) {
            binding.apply {
                // Bind playlist properties to UI elements
                playlistName.text = playlist.nameOfSong
                playlistFollowers.text = playlist.numberOfFollowers.toString()

                // Load image from URL
                Picasso.get().load(playlist.dummyImageUrl).into(playlistImage)
            }

            itemView.setOnClickListener {
                // Navigate to PlaylistDetailFragment with selected playlist
            }
        }
    }

    class PlaylistDiffCallback : DiffUtil.ItemCallback<PlaylistResponse.Playlist>() {
        override fun areItemsTheSame(oldItem: PlaylistResponse.Playlist, newItem: PlaylistResponse.Playlist): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PlaylistResponse.Playlist, newItem: PlaylistResponse.Playlist): Boolean {
            return oldItem == newItem
        }
    }
}*/