package com.example.toastgoand.home.clanframes

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ClanFrameDataClass (
    val id: Int,
    val club_name: Int,
    val published_date: String,
    val frame_picture: String?,
    val start_time: String,
    val end_time: String,
    val channel_id: String,
    val frame_picture_link: String,
    val frame_status: Boolean
)