package com.example.banquemisrchallenge05.ui.repository

import com.example.banquemisrchallenge05.data.network.ResponseResult
import com.example.banquemisrchallenge05.data.remote.RemoteDataSource
import com.example.banquemisrchallenge05.data.repository.Repository
import com.example.banquemisrchallenge05.data.repository.RepositoryImpl
import com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel.MockData
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class TestRepo {
    private lateinit var repository: Repository
    private lateinit var fakeRemoteDataSource: RemoteDataSource

    @Before
    fun setUp() {
        mockkStatic(android.util.Log::class)
        every { android.util.Log.isLoggable(any(), any()) } returns false

        fakeRemoteDataSource = FakeRemoteDataSource()
        repository = RepositoryImpl(fakeRemoteDataSource)
    }

    @Test
    fun test_getNowPlayingMovies() = runTest {
        val result = repository.getNowPlayingMovies().first()
        if (result is ResponseResult.Success<*>) {
            assertEquals(result.data, MockData.mockMovieResponse.results)
        }
    }

    @Test
    fun test_getUpComingMovies() = runTest {
        val result = repository.getUpcomingMovies().first()
        if (result is ResponseResult.Success<*>) {
            assertEquals(result.data, MockData.mockMovieResponse.results)
        }
    }

    @Test
    fun test_getPopularMovies() = runTest {
        val result = repository.getPopularMovies().first()
        if (result is ResponseResult.Success<*>) {
            assertEquals(result.data, MockData.mockMovieResponse.results)
        }
    }
}