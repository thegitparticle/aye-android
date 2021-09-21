package com.example.toastgoand.network

import androidx.room.TypeConverter
import com.example.toastgoand.home.clans.DisplayPhoto
import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
import com.example.toastgoand.network.directs.DisplayGuys
import com.example.toastgoand.network.myclans.DisplayPhotos
import com.example.toastgoand.network.userdetails.User
import com.fasterxml.jackson.annotation.JsonSubTypes
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken




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

    @TypeConverter
    fun fromListMyDirects(value : DisplayGuys) = Json.encodeToString(value)

    @TypeConverter
    fun toListMyDirects(value: String) = Json.decodeFromString<DisplayGuys>(value)

    @TypeConverter
    fun fromListDefaultRecosList(value: List<String>) = Json.encodeToString(value)

    @TypeConverter
    fun toListDefaultRecosList(value: String) = Json.decodeFromString<List<String>>(value)

}