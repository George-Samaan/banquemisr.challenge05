package com.example.banquemisrchallenge05.data.model

data class MovieDetailsResponse(
    val genres: List<Genre>,
    val id: Int,
    val original_language: String,
    val overview: String?,
    val poster_path: String?,
    val release_date: String,
    val runtime: Int?,
    val status: String,
    val tagline: String?,
    val title: String,
    val vote_average: Double,
    val vote_count: Int
)

data class Genre(
    val id: Int,
    val name: String
)
