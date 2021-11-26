package com.example.toastgoand.home.clantalk.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class NewClanFrameDataClass (
    @SerializedName("start_time") val start_time: String?,
    @SerializedName("end_time") val end_time: String?,
    @SerializedName("club_name") val club_name: Int?,
    @SerializedName("channel_id") val channel_id: String?,
        )