package com.example.toastgoand.home.startclan.network

import com.google.gson.annotations.SerializedName

data class StartClanResponseDataClass(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("members") val members: String?,
    @SerializedName("admin_leader") val admin_leader: Int?,
    @SerializedName("pn_channel_id") val pn_channel_id: String?
)
