package com.example.toastgoand.home.directtalk.network

import com.google.gson.annotations.SerializedName

data class NewDirectFrameDataClass (
    @SerializedName("start_time") val start_time: String?,
    @SerializedName("end_time") val end_time: String?,
    @SerializedName("users") val users: String?,
    @SerializedName("channel_id") val channel_id: String?,
)