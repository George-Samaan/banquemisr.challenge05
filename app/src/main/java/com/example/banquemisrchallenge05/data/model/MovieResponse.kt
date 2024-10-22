package com.example.banquemisrchallenge05.data.model

data class MovieResponse(
    val results: List<Movie>,
    val totalResults: Int,
    val totalPages: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)