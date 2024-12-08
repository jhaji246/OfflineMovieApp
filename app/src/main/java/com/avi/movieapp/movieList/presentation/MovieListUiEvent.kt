package com.avi.movieapp.movieList.presentation

sealed interface MovieListUiEvent {
    data class UpdateSearchQuery(val query: String) : MovieListUiEvent
    data class Paginate(val category: String) : MovieListUiEvent
    object Navigate : MovieListUiEvent
}