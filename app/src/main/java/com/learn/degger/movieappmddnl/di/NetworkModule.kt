package com.learn.degger.movieappmddnl.di

import com.learn.degger.movieappmddnl.network.MovieApiServices
import com.learn.degger.movieappmddnl.utilis.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    fun providesUrl() = Constants.BaseUrl

    @Provides
    @Singleton
    fun providesApiService(url:String) : MovieApiServices =
        Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
            .create(MovieApiServices::class.java)
}