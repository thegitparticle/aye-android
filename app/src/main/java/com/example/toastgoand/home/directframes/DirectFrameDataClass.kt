package com.example.toastgoand.home.directframes


data class DirectFrameDataClass (
    val id: Int,
    val published_date: String,
    val frame_picture: String?,
    val start_time: String,
    val end_time: String,
    val channel_id: String,
    val users: String,
    val frame_picture_link: String,
    val frame_status: Boolean
        )