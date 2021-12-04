package com.example.toastgoand.auth.network.dataclasses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class OtpSignUpDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("password") val password: String?,
)
