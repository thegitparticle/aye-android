package com.example.toastgoand.network.userdetails

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


@Entity(
    tableName = "userDetailsTable"
)

data class UserDetailsDataClass (
    @ColumnInfo(name = "user") val user : User,
    @ColumnInfo(name = "bio") val bio : String,
    @ColumnInfo(name = "image") val image : String,
    @ColumnInfo(name = "id") val id : Int
)

data class User (
     val username : String,
     val phone : String,
     val full_name : String,
     val id : Int,
     val clubs_joined_by_user : String,
     val number_of_clubs_joined : Int,
     val contact_list : String,
     val total_frames_participation : Int,
     val country_code_of_user : String,
     val contact_list_sync_status : Boolean
)