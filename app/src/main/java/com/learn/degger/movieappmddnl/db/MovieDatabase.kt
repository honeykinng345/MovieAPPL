package com.learn.degger.movieappmddnl.db


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.learn.degger.movieappmddnl.model.Movie

@Database(entities = [Movie::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class MovieDatabase : RoomDatabase() {
    // public abstract MovieDao movieDao();
    abstract fun wishListDao(): WishListDao
}