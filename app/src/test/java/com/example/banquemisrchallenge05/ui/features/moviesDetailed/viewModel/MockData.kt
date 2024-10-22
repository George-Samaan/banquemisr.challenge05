package com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel

import com.example.banquemisrchallenge05.data.model.Genre
import com.example.banquemisrchallenge05.data.model.Movie
import com.example.banquemisrchallenge05.data.model.MovieDetailsResponse
import com.example.banquemisrchallenge05.data.model.MovieResponse

object MockData {
    val fakeMovies = listOf(
        Movie(
            id = 1,
            title = "The Great Adventure",
            overview = "Join our heroes as they embark on a journey through uncharted lands.",
            poster_path = "/images/great_adventure.jpg",
            release_date = "2023-06-15"
        ),
        Movie(
            id = 2,
            title = "Mystery of the Lost Artifact",
            overview = "A thrilling mystery unfolds as archaeologists uncover ancient secrets.",
            poster_path = "/images/lost_artifact.jpg",
            release_date = "2022-11-20"
        ),
        Movie(
            id = 3,
            title = "Love in the Time of Chaos",
            overview = "A romantic tale set against the backdrop of a world in turmoil.",
            poster_path = "/images/love_chaos.jpg",
            release_date = "2024-02-14"
        ),
        Movie(
            id = 4,
            title = "The Last Stand",
            overview = "In a dystopian future, one warrior fights for humanity's survival.",
            poster_path = "/images/last_stand.jpg",
            release_date = "2023-08-30"
        ),
        Movie(
            id = 5,
            title = "Into the Unknown",
            overview = "An epic journey through the cosmos, discovering new worlds and civilizations.",
            poster_path = "/images/into_unknown.jpg",
            release_date = "2025-01-01"
        )
    )

    var mockMovieDetailsResponse = listOf(
        MovieDetailsResponse(
            genres = listOf(
                Genre(id = 1, name = "Adventure"),
                Genre(id = 2, name = "Fantasy"),
                Genre(id = 3, name = "Action")
            ),
            id = 1,
            original_language = "en",
            overview = "In a world filled with magic, one hero embarks on an epic journey to save the kingdom.",
            poster_path = "/images/mock_movie_poster.jpg",
            release_date = "2023-06-15",
            runtime = 120,
            status = "Released",
            tagline = "The adventure begins.",
            title = "The Great Adventure",
            vote_average = 8.5,
            vote_count = 1500
        ),
        MovieDetailsResponse(
            genres = listOf(Genre(1, "Adventure")),
            id = 1,
            original_language = "en",
            overview = "Adventure movie 1",
            poster_path = "/images/movie1.jpg",
            release_date = "2023-01-01",
            runtime = 120,
            status = "Released",
            tagline = "Adventure awaits.",
            title = "Adventure 1",
            vote_average = 7.5,
            vote_count = 500
        ),
        MovieDetailsResponse(
            genres = listOf(Genre(2, "Action")),
            id = 2,
            original_language = "en",
            overview = "Action movie 2",
            poster_path = "/images/movie2.jpg",
            release_date = "2023-02-01",
            runtime = 150,
            status = "Released",
            tagline = "Action packed.",
            title = "Action 2",
            vote_average = 8.0,
            vote_count = 600
        ),
    )
    val mockMovieResponse = MovieResponse(
        results = listOf(
            Movie(
                id = 1,
                title = "The Shawshank Redemption",
                overview = "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.",
                poster_path = "/q6y0Go1tsGEsmtFryDOJo3dEmqu.jpg",
                release_date = "1994-09-23"
            ),
            Movie(
                id = 2,
                title = "The Godfather",
                overview = "The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.",
                poster_path = "/rPdtLWNsZmAtoZl9PK7S2wE3qiS.jpg",
                release_date = "1972-03-14"
            ),
            Movie(
                id = 3,
                title = "The Dark Knight",
                overview = "When the menace known as the Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.",
                poster_path = "/qJ2tW6WMUDux911r6m7haRef0WH.jpg",
                release_date = "2008-07-18"
            ),
            Movie(
                id = 4,
                title = "Pulp Fiction",
                overview = "The lives of two mob hitmen, a boxer, a gangster and his wife intertwine in four tales of violence and redemption.",
                poster_path = "/d5iIlFn5s0ImszYzBPb8JPIfbXD.jpg",
                release_date = "1994-10-14"
            ),
            Movie(
                id = 5,
                title = "Inception",
                overview = "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a CEO.",
                poster_path = "/9gk7adHYeDvHkCSEqAvQNLV5Uge.jpg",
                release_date = "2010-07-16"
            )
        ),
        totalResults = 5,
        totalPages = 1
    )
}