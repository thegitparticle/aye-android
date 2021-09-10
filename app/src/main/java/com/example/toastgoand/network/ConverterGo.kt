package com.example.toastgoand.network

import androidx.room.TypeConverter
import com.example.toastgoand.network.userdetails.User
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class ConverterGo {
    @TypeConverter
    fun fromList(value : User) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<User>(value)
}