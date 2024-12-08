package com.avi.movieapp.movieList.presentation

import com.avi.movieapp.movieList.domain.model.Movie

data class MovieListState(
    val isLoading: Boolean = false,
    val searchQuery: String = "",

    val popularMovieListPage: Int = 1,
    val upcomingMovieListPage: Int = 1,

    val isCurrentPopularScreen: Boolean = true,

    val popularMovieList: List<Movie> = emptyList(),
    val upcomingMovieList: List<Movie> = emptyList(),
) {
    val filteredMovies: List<Movie>
        get() = if (searchQuery.isBlank()) popularMovieList
        else popularMovieList.filter { it.title.contains(searchQuery, ignoreCase = true) }
}
