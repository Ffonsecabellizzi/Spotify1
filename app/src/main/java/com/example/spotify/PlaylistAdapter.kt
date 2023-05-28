package com.example.spotify

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
}