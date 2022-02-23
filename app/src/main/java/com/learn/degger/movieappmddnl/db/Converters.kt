package com.learn.degger.movieappmddnl.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*

class Converters {



    @TypeConverter
    fun storedStringToMyObjects(data: String): List<Int> {
        val gson = Gson()
        if (data == null) {
            return Collections.emptyList()
        }
        val listType: Type = object : TypeToken<List<Int>>() {

        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun myObjectsToStoredString(myObjects: List<Int>): String {
        val gson = Gson()
        return gson.toJson(myObjects)
    }
}