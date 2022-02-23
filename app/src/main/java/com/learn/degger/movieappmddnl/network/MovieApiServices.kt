package com.learn.degger.movieappmddnl.network

import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.model.MovieResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface MovieApiServices {
    @GET("movie/popular")
    suspend fun getPopularMovies(@QueryMap queries :HashMap<String,String>): Response<MovieResponse>

    @GET("movie/upcoming")
    suspend fun getUpCompingMovies(@QueryMap queries :HashMap<String,String>): Response<MovieResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path ("movie_id") id: Int ,@QueryMap queries :HashMap<String,String>): Response<Movie>



    @GET("movie/top_rated")
    fun getTopRated(@QueryMap queries: HashMap<String, String>): Observable<MovieResponse>
}