package com.example.toastgoand.home.clans

import com.beust.klaxon.*

private val klaxon = Klaxon()

data class MyClanDataClass (
    @Json(name = "club_profile_pic")
    val clubProfilePic: String,

    @Json(name = "club_name")
    val clubName: String,

    @Json(name = "club_id")
    val clubID: Long,

    @Json(name = "pn_channel_id")
    val pnChannelID: String,

    @Json(name = "club_members")
    val clubMembers: String,

    @Json(name = "frame_total")
    val frameTotal: Long,

    @Json(name = "ongoing_frame")
    val ongoingFrame: Boolean,

    @Json(name = "start_time")
    val startTime: String,

    @Json(name = "end_time")
    val endTime: String,

    @Json(name = "display_photos")
    val displayPhotos: List<DisplayPhoto>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<MyClanDataClass>(json)
    }
}

data class DisplayPhoto (
    @Json(name = "user_id")
    val userID: Int,

    @Json(name = "display_pic")
    val displayPic: String
)
