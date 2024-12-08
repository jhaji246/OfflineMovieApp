package com.avi.movieapp.details.presentation

import com.avi.movieapp.movieList.domain.model.Movie

data class DetailsState(
    val isLoading: Boolean = false,
    val movie: Movie? = null,
)
