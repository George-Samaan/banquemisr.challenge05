package com.example.banquemisrchallenge05.data.model

data class MovieResponse(
    val results: List<Movie>,
    val page: Int,
    val total_pages: Int,
    val total_results: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val poster_path: String,
    val release_date: String
)
