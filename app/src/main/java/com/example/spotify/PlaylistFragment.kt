package com.example.spotify


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import java.io.IOException

class PlaylistFragment : Fragment(), PlaylistAdapter.OnPlaylistClickListener {

    private lateinit var adapter: PlaylistAdapter
    private lateinit var rvPlaylists: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_playlist, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rvPlaylists = view.findViewById(R.id.rv_playlists)
        val playlists = loadPlaylistsFromJson()
        if (playlists != null) {
            adapter = PlaylistAdapter(this)
            rvPlaylists.layoutManager = GridLayoutManager(context, 2)
            rvPlaylists.adapter = adapter
            adapter.submitList(playlists)
        }
    }

    private fun loadPlaylistsFromJson(): List<PlaylistResponse.Playlist>? {
        val jsonFileString = getJsonDataFromAsset(requireContext(), "playlist.json")
        val gson = Gson()
        val playlistResponse: PlaylistResponse = gson.fromJson(jsonFileString, PlaylistResponse::class.java)
        return playlistResponse.data
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }

    override fun onPlaylistClick(playlist: PlaylistResponse.Playlist) {
        val songsFragment = SongsFragment(playlist)
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.containerPlaylist, songsFragment)
            .addToBackStack(null)
            .commit()
    }
}
