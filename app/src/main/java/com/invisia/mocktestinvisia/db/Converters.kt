package com.invisia.mocktestinvisia.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

// Create a TypeConverter object to convert between List<Int> and String.
object Converters {
    // Convert a List<Int> to a JSON string.
    @TypeConverter
    @JvmStatic
    fun fromIntegerList(value: List<Int>?): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    // Convert a JSON string to a List<Int>.
    @TypeConverter
    @JvmStatic
    fun toIntegerList(value: String?): List<Int>? {
        val gson = Gson()
        val type = object : TypeToken<List<Int>>() {}.type
        return gson.fromJson(value, type)
    }
}
