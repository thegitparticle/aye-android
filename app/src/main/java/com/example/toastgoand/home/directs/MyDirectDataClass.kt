package com.example.toastgoand.home.directs

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class MyDirectDataClass (
    @Json(name = "direct_channel_id")
    val directChannelID: String,

    @Json(name = "display_guys")
    val displayGuys: DisplayGuys,

    @Json(name = "frame_total")
    val frameTotal: Int,

    @Json(name = "ongoing_frame")
    val ongoingFrame: Boolean,

    @Json(name = "start_time")
    val startTime: Boolean,

    @Json(name = "end_time")
    val endTime: Boolean
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<MyDirectDataClass>(json)
    }
}

data class DisplayGuys (
    @Json(name = "user_id")
    val userID: String,

    @Json(name = "profile_picture")
    val profilePicture: String,

    @Json(name = "full_name")
    val fullName: String
)
