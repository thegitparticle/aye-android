package com.example.toastgoand.network.myclans

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "myClansTable"
)

data class MyClansDataClass(
    val club_profile_pic: String,
    val club_name: String,
    @PrimaryKey val club_id: Int,
    val pn_channel_id: String,
    val club_members: String,
    val frame_total: Int,
    val ongoing_frame: Boolean,
    val start_time: String,
    val end_time: String,
    val display_photos: MutableList<DisplayPhotos>?
)

@Serializable
data class DisplayPhotos(
    val user_id: Int,
    val display_pic: String
)