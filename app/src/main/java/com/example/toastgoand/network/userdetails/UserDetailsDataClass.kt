package com.example.toastgoand.network.userdetails

import androidx.annotation.Keep
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity(
    tableName = "userDetailsTable"
)

@Keep
@Serializable
data class UserDetailsDataClass (
    @ColumnInfo(name = "user") val user : User,
    @ColumnInfo(name = "bio") val bio : String,
    @ColumnInfo(name = "image") val image : String,
    @PrimaryKey @ColumnInfo(name = "id") val id : Int
)

@Keep
@Serializable
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