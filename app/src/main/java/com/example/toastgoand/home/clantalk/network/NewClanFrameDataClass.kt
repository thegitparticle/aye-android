package com.example.toastgoand.home.clantalk.network

import com.google.gson.annotations.SerializedName

data class NewClanFrameDataClass (
    @SerializedName("start_time") val start_time: String?,
    @SerializedName("end_time") val end_time: String?,
    @SerializedName("club_name") val club_name: Int?,
    @SerializedName("channel_id") val channel_id: String?,
        )