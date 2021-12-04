package com.example.toastgoand.auth.otplogin.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class OTPLoginDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("password") val password: String?,
)
