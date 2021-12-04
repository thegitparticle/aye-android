package com.example.toastgoand.home.clans

import androidx.annotation.Keep
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import kotlinx.serialization.Serializable

private val klaxon = Klaxon()

@Keep
@Serializable
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

@Keep
@Serializable
data class DisplayPhoto (
    @Json(name = "user_id")
    val userID: Int,

    @Json(name = "display_pic")
    val displayPic: String
)
