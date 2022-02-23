package com.learn.degger.movieappmddnl.di

import dagger.hilt.InstallIn
import javax.inject.Singleton
import android.app.Application
import androidx.room.Room
import com.learn.degger.movieappmddnl.db.MovieDatabase
import com.learn.degger.movieappmddnl.db.WishListDao
import com.learn.degger.movieappmddnl.utilis.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    @Singleton
    fun provideMovieDatabase(application: Application?): MovieDatabase {
        return Room.databaseBuilder(application?.applicationContext!!, MovieDatabase::class.java, Constants.DataBaseName)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun provideWishListDao(movieDatabase: MovieDatabase): WishListDao {
        return movieDatabase.wishListDao()
    }
}