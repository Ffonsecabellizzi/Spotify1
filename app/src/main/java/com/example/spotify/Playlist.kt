package com.example.spotify

data class Playlist(
    val id: Int,
    val name_of_song: String,
    val number_of_followers: Int,
    val dummy_image_url: String,
    val songs: List<Song>
)