package com.example.toastgoand.home.clanhub

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ClanDetailsDataClass (
    val id : Int,
    val name : String,
    val member_count : Int,
    val date_created : String,
    val profile_picture : String,
    val frames_total : Int,
    val members : String,
    val admin_leader : String,
    val users : List<ClanMember>
    )

@Keep
@Serializable
data class ClanMember (
    val user_id : Int,
    val username : String,
    val name : String,
    val display_pic : String
)