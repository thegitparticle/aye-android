package com.example.toastgoand.home.clanhub

import androidx.annotation.Keep
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import kotlinx.serialization.Serializable

private val klaxon = Klaxon()

@Keep
@Serializable
data class ClanHubDataClass (
    val id: Long,
    val name: String,

    @Json(name = "member_count")
    val memberCount: Long,

    @Json(name = "date_created")
    val dateCreated: String,

    @Json(name = "profile_picture")
    val profilePicture: String,

    @Json(name = "frames_total")
    val framesTotal: Long,

    val members: String,

    @Json(name = "admin_leader")
    val adminLeader: String,

    val users: List<User>
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<ClanHubDataClass>(json)
    }
}

@Keep
@Serializable
data class User (
    @Json(name = "user_id")
    val userID: Long,

    val username: String,
    val name: String,

    @Json(name = "display_pic")
    val displayPic: String
)