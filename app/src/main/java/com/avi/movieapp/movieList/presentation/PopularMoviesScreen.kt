package com.avi.movieapp.movieList.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.avi.movieapp.movieList.presentation.components.MovieItem
import com.avi.movieapp.movieList.util.Category

@Composable
fun PopularMoviesScreen(
    movieListState: MovieListState,
    navController: NavHostController,
    onEvent: (MovieListUiEvent) -> Unit
) {

    Column {
        // Search Bar
        BasicTextField(
            value = movieListState.searchQuery,
            onValueChange = { query -> onEvent(MovieListUiEvent.UpdateSearchQuery(query)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                        .padding(8.dp)
                ) {
                    if (movieListState.searchQuery.isBlank()) {
                        Text("Search movies...", color = Color.Gray)
                    }
                    innerTextField()
                }
            }
        )

        if (movieListState.popularMovieList.isEmpty()) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(vertical = 8.dp, horizontal = 4.dp)
            ) {
                items(movieListState.filteredMovies.size) { index ->
                    MovieItem(
                        movie = movieListState.filteredMovies[index],
                        navHostController = navController
                    )
                    Spacer(modifier = Modifier.height(16.dp))

                    if (index >= movieListState.popularMovieList.size - 1 && !movieListState.isLoading) {
                        onEvent(MovieListUiEvent.Paginate(Category.POPULAR))
                    }

                }
            }
        }
    }


}