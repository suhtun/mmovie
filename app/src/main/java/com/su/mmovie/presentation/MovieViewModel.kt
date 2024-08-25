package com.su.mmovie.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.su.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    var state by mutableStateOf(MovieState())
        private set

    init {
        fetchUpcomingMovies()
    }

    private fun fetchUpcomingMovies() {
        viewModelScope.launch {
            state = state.copy(isLoading = true, errorMessage = null)

            when (val result = repository.getMovieLists()) {
                is com.su.common.Resource.Success -> {
                    state = state.copy(
                        isLoading = false,
                        movieLists = result.data
                    )
                }

                is com.su.common.Resource.Error -> {
                    state = state.copy(
                        isLoading = false,
                        errorMessage = result.message
                    )
                }
            }
        }
    }
}

data class MovieState(
    val isLoading: Boolean = false,
    val movieLists: com.su.model.MovieLists? = null,
    val errorMessage: String? = null
)