package com.example.toastgoand.home.clanframes

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class ClanFrameDataClass (
    val id: Long,

    @Json(name = "club_name")
    val clubName: Long,

    @Json(name = "published_date")
    val publishedDate: String,

    @Json(name = "frame_picture")
    val framePicture: Any? = null,

    @Json(name = "frame_status")
    val frameStatus: Boolean,

    @Json(name = "channel_id")
    val channelID: String,

    @Json(name = "start_time")
    val startTime: String,

    @Json(name = "end_time")
    val endTime: String,

    @Json(name = "frame_picture_link")
    val framePictureLink: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<ClanFrameDataClass>(json)
    }
}
