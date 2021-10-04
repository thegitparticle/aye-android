package com.example.toastgoand.auth.network.dataclasses

import com.google.gson.annotations.SerializedName

data class OtpSignUpDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("password") val password: String?,
)
