package com.example.spotify

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar

class SongsFragment(private val playlist: PlaylistResponse.Playlist) : Fragment(), SongAdapter.OnSongClickListener {

    private lateinit var adapter: SongAdapter
    private lateinit var rvSongs: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_songs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvSongs = view.findViewById(R.id.rv_songs)

        adapter = SongAdapter(playlist.songs, this)
        rvSongs.layoutManager = LinearLayoutManager(context)
        rvSongs.adapter = adapter
    }

    override fun onSongClick(song: PlaylistResponse.Playlist.Song) {
        // Aquí puedes implementar la funcionalidad para reproducir la canción seleccionada.
    }

    override fun onFavoriteClick(song: PlaylistResponse.Playlist.Song) {
        Snackbar.make(requireView(), "${song.name} de ${song.artist} se ha añadido a favoritos", Snackbar.LENGTH_SHORT).show()
    }
}