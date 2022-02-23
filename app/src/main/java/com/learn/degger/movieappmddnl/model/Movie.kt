package com.learn.degger.movieappmddnl.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue
import java.util.ArrayList
@Parcelize
@Entity(tableName = "wishlist_table")
class Movie(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    val  idAuto : Int,
    @SerializedName("adult") val adult : Boolean,
    @SerializedName("backdrop_path") val backdrop_path : String,
    @SerializedName("genre_ids") val genre_ids : List<Int>,
    @SerializedName("id") val id : Int,
    @SerializedName("original_language") val original_language : String,
    @SerializedName("original_title") val original_title : String,
    @SerializedName("overview") val overview : String,
    @SerializedName("popularity") val popularity : Double,
    @SerializedName("poster_path") val poster_path : String,
    @SerializedName("release_date") val release_date : String,
    @SerializedName("title") val title : String,
    @SerializedName("video") val video : Boolean,
    @SerializedName("vote_average") val vote_average : Double,
    @SerializedName("vote_count") val vote_count : Int,
    var vidoes: @RawValue JsonObject? = null

):Parcelable


