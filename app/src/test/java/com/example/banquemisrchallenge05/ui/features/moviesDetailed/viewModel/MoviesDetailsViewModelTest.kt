package com.example.banquemisrchallenge05.ui.features.moviesDetailed.viewModel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.banquemisrchallenge05.data.network.ApiState
import com.example.banquemisrchallenge05.data.repository.Repository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class MoviesDetailsViewModelTest {
    private lateinit var viewModel: MoviesDetailsViewModel
    private lateinit var repository: Repository

    @Before
    fun setUp() {
        repository = FakeRepo()
        viewModel = MoviesDetailsViewModel(repository)
    }

    // test for fetching data with a valid ID
    @Test
    fun test_getCertainMovieDetailsByID() = runTest {
        val expectedMovieDetails = MockData.mockMovieDetailsResponse.first()
        viewModel.getMovieDetails(expectedMovieDetails.id)
        assertEquals(
            expectedMovieDetails,
            (viewModel.movieDetails.value as ApiState.Success).data
        )
    }

    // test for fetching data with an invalid ID
    @Test
    fun test_getCertainMovieDetailsByID_Error() = runTest {
        val id = 90
        val expectedErrorMessage = "Movie with ID $id not found."
        viewModel.getMovieDetails(id)
        assertEquals(
            expectedErrorMessage,
            (viewModel.movieDetails.value as ApiState.Failure).message
        )
    }


    // Clear the mock movie details list (if your MockData is mutable)
    @Test
    fun test_getMovieDetails_EmptyList() = runTest {
        MockData.mockMovieDetailsResponse = emptyList()
        val id = 1
        val expectedErrorMessage = "Movie with ID $id not found."

        viewModel.getMovieDetails(id)
        assertEquals(
            expectedErrorMessage,
            (viewModel.movieDetails.value as ApiState.Failure).message
        )
    }

    // Extract all valid IDs from mock data
    @Test
    fun test_getMultipleMovieDetails_ValidIDs() = runTest {
        val movieIds = MockData.mockMovieDetailsResponse.map { it.id }

        for (id in movieIds) {
            viewModel.getMovieDetails(id)

            val expectedMovieDetails = MockData.mockMovieDetailsResponse.find { it.id == id }
            assertNotNull(expectedMovieDetails)

            assertEquals(
                expectedMovieDetails,
                (viewModel.movieDetails.value as ApiState.Success).data
            )
        }
    }

    //Test for Handling a Valid ID After an Invalid Request
    @Test
    fun test_getMovieDetails_ValidAfterInvalid() = runTest {
        val invalidId = 90
        val expectedErrorMessage = "Movie with ID $invalidId not found."
        viewModel.getMovieDetails(invalidId)

        assertEquals(
            expectedErrorMessage,
            (viewModel.movieDetails.value as ApiState.Failure).message
        )

        val validId = MockData.mockMovieDetailsResponse.first().id
        viewModel.getMovieDetails(validId)

        val expectedMovieDetails = MockData.mockMovieDetailsResponse.first()
        assertEquals(
            expectedMovieDetails,
            (viewModel.movieDetails.value as ApiState.Success).data
        )
    }

    //Test for State Before and After Fetching
    @Test
    fun test_initialStateBeforeFetching() = runTest {
        assertTrue(viewModel.movieDetails.value is ApiState.Loading)

        val validId = MockData.mockMovieDetailsResponse.first().id
        viewModel.getMovieDetails(validId)

        assertTrue(viewModel.movieDetails.value is ApiState.Success)
    }
}