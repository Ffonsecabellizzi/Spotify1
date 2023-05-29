package com.example.spotify

import com.google.gson.annotations.SerializedName

data class PlaylistResponse(
    val data: List<Playlist>,
    val pages: Int
) {
    data class Playlist(
        @SerializedName("dummy_image_url")
        val dummyImageUrl: String,
        val id: Int,
        @SerializedName("name_of_song")
        val nameOfSong: String,
        @SerializedName("number_of_followers")
        val numberOfFollowers: Int,
        val songs: List<Song>
    ) {
        data class Song(
            @SerializedName("dummy_image_url")
            val dummyImageUrl: String,
            val artist: String,
            val name: String,
            val url: String
        )
    }
}