package com.example.toastgoand.home.clanhub

import com.beust.klaxon.*

private val klaxon = Klaxon()

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

data class User (
    @Json(name = "user_id")
    val userID: Long,

    val username: String,
    val name: String,

    @Json(name = "display_pic")
    val displayPic: String
)