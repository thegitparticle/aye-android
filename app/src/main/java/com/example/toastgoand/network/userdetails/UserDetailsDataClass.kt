package com.example.toastgoand.network.userdetails

import com.squareup.moshi.Json

data class UserDetailsDataClass (
    val user : User,
    val bio : String,
    val image : String,
    val id : Int
)

data class User (

    val username : String,
    val phone : Int,
    val full_name : String,
    val id : Int,
    val clubs_joined_by_user : String,
    val number_of_clubs_joined : Int,
    val contact_list : String,
    val total_frames_participation : Int,
    val country_code_of_user : String,
    val contact_list_sync_status : Boolean
)