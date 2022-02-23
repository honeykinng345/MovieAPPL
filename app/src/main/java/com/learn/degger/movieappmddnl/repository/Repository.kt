package com.learn.degger.movieappmddnl.repository

import com.learn.degger.movieappmddnl.model.Movie
import com.learn.degger.movieappmddnl.model.MovieResponse
import com.learn.degger.movieappmddnl.network.MovieApiServices
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.util.HashMap
import javax.inject.Inject

class Repository
@Inject
constructor(private val apiServiceImpl: MovieApiServices) {

    fun getPopularMovies(hashMap: HashMap<String,String>): Flow<Response<MovieResponse>> =
        flow {
        emit(apiServiceImpl.getPopularMovies(hashMap))
    }.flowOn(Dispatchers.IO)


    fun getUpComingMovies(hashMap: HashMap<String,String>): Flow<Response<MovieResponse>> =
        flow {
            emit(apiServiceImpl.getUpCompingMovies(hashMap))
        }.flowOn(Dispatchers.IO)

    fun getMovieDetails(moivieid :Int ,hashMap: HashMap<String,String>): Flow<Response<Movie>> =
        flow {
            emit(apiServiceImpl.getMovieDetails(moivieid,hashMap))
        }.flowOn(Dispatchers.IO)


    fun getTopRated(map: HashMap<String, String>): Observable<MovieResponse> {
        return apiServiceImpl.getTopRated(map)
    }
    }

