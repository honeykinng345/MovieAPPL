package com.learn.degger.movieappmddnl.db

import androidx.room.Dao
import androidx.lifecycle.LiveData
import androidx.room.Insert
import androidx.room.Query
import com.learn.degger.movieappmddnl.model.Movie

@Dao
interface WishListDao {
    @Insert
    fun insert(wishListMovie: Movie)

    @Query("DELETE From wishlist_table WHERE idAuto = :movieId")
    fun delete(movieId: Int)

    @Query("DELETE FROM wishlist_table")
    fun clearWishList()

    @get:Query("SELECT * FROM wishlist_table")
    val wishList: LiveData<List<Movie>>

    @Query("SELECT * FROM wishlist_table WHERE id = :movieId ")
    fun getWishListMovie(movieId: Int): Movie?
}