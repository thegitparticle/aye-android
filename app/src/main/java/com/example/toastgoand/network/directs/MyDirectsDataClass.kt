package com.example.toastgoand.network.directs

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "myDirectsTable"
)

data class MyDirectsDataClass (
    @PrimaryKey val direct_channel_id : String,
    val display_guys : DisplayGuys,
    val frame_total : Int,
    val ongoing_frame : Boolean,
    val start_time : Boolean,
    val end_time : Boolean
        )

@Serializable
data class DisplayGuys (
    val user_id : Int,
    val profile_picture : String,
    val full_name : String
)