package com.example.streammovies.response

data class PostModel (
    val id: Int? = null,
    val title: String,
    val poster_path: String? = null,
    val overview: String? = null,
    val video: String
)