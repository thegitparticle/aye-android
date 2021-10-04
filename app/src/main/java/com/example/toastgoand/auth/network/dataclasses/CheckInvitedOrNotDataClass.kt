package com.example.toastgoand.auth.network.dataclasses

import com.google.gson.annotations.SerializedName

data class CheckInvitedOrNotDataClass(
    @SerializedName("invited_to_clubs_id") val invited_to_clubs_id: String?,
    @SerializedName("invited_to_clubs_name") val invited_to_clubs_name: String?,
    @SerializedName("invited_by_user") val invited_by_user: String?
)
