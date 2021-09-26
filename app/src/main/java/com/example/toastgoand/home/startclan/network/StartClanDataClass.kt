package com.example.toastgoand.home.startclan.network

import com.google.gson.annotations.SerializedName

data class StartClanDataClass(
    @SerializedName("name") val name: String?,
    @SerializedName("members") val members: String?,
    @SerializedName("admin_leader") val admin_leader: Int?,
)