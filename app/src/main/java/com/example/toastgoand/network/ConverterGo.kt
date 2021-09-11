package com.example.toastgoand.network

import androidx.room.TypeConverter
import com.example.toastgoand.home.clans.DisplayPhoto
import com.example.toastgoand.network.myclans.DisplayPhotos
import com.example.toastgoand.network.userdetails.User
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
class ConverterGo {
    @TypeConverter
    fun fromList(value : User) = Json.encodeToString(value)

    @TypeConverter
    fun toList(value: String) = Json.decodeFromString<User>(value)

    @TypeConverter
    fun fromListMyClans(value : MutableList<DisplayPhotos>) = Json.encodeToString(value)

    @TypeConverter
    fun toListMyClans(value: String) = Json.decodeFromString<MutableList<DisplayPhotos>>(value)
}