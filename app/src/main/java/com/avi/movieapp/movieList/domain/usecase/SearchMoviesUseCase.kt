package com.avi.movieapp.movieList.domain.usecase

import com.avi.movieapp.movieList.domain.model.Movie
import com.avi.movieapp.movieList.domain.repository.MovieListRepository
import javax.inject.Inject

class SearchMoviesUseCase @Inject constructor(
    private val repository: MovieListRepository
) {
    suspend operator fun invoke(query: String): List<Movie> {
        if (query.isBlank()) return emptyList()
        return repository.searchMovies(query)
    }
}