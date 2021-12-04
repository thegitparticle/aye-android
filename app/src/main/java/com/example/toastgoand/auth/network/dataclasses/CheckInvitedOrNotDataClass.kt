package com.example.toastgoand.auth.network.dataclasses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class CheckInvitedOrNotDataClass(
    @SerializedName("invited_to_clubs_id") val invited_to_clubs_id: String?,
    @SerializedName("invited_to_clubs_name") val invited_to_clubs_name: String?,
    @SerializedName("invited_by_user") val invited_by_user: String?
)
