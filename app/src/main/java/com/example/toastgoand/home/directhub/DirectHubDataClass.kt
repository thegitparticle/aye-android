package com.example.toastgoand.home.directhub

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

data class DirectHubDataClass (
    val user: User,
    val bio: String,
    val image: String,
    val id: Long
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<DirectHubDataClass>(json)
    }
}

data class User (
    val username: String,
    val phone: String,

    @Json(name = "full_name")
    val fullName: String,

    val id: Long,

    @Json(name = "clubs_joined_by_user")
    val clubsJoinedByUser: String,

    @Json(name = "number_of_clubs_joined")
    val numberOfClubsJoined: Long,

    @Json(name = "contact_list")
    val contactList: String,

    @Json(name = "total_frames_participation")
    val totalFramesParticipation: Long,

    @Json(name = "country_code_of_user")
    val countryCodeOfUser: String,

    @Json(name = "contact_list_sync_status")
    val contactListSyncStatus: Boolean
)
