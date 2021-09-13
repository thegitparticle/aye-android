package com.example.toastgoand.home.otherprofile

data class OtherProfileDataClass(
    val user: DepthDetails,
    val bio: String,
    val image: String,
    val id: Int
)

data class DepthDetails(
    val username: String,
    val phone: String,
    val full_name: String,
    val id: Int,
    val clubs_joined_by_user: String,
    val number_of_clubs_joined: Int,
    val contact_list: String,
    val total_frames_participation: Int,
    val country_code_of_user: String,
    val contact_list_sync_status: Boolean
)

