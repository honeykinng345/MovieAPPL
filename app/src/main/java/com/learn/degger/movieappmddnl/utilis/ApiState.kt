package com.learn.degger.movieappmddnl.utilis

import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.model.MovieResponse
import retrofit2.Response

sealed class ApiState {

    object Loading : ApiState()
    class Failure(val msg: Throwable) : ApiState()
    class Success(val data: List<Movie>) : ApiState()
    object Empty : ApiState()
    class Data(val data: Movie) : ApiState()
}