package com.example.toastgoand.network.myclans

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "myClansTable"
)

@Keep
@Serializable
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
    val on_going_stream_status: Boolean,
    val stream_started_by: String,
    val display_photos: MutableList<DisplayPhotos>?
)

@Keep
@Serializable
data class DisplayPhotos(
    val user_id: Int,
    val display_pic: String
)