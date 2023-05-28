package com.example.spotify

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.os.Bundle
import com.example.spotify.R
import com.google.gson.Gson
import java.io.IOException
import java.io.InputStream

class HomeFragment : Fragment() {
    private lateinit var playlistAdapter: PlaylistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        setupRecyclerView(view)
        return view
    }

    private fun setupRecyclerView(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = GridLayoutManager(this.context, 2)

        playlistAdapter = PlaylistAdapter()
        recyclerView.adapter = playlistAdapter

        // Use your method to load playlists from JSON
        val playlists = loadPlaylistsFromJson()
        playlistAdapter.submitList(playlists)
    }

    private fun loadPlaylistsFromJson(): List<PlaylistResponse.Playlist> {
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
}