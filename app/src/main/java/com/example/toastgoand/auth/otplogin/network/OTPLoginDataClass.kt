package com.example.toastgoand.auth.otplogin.network

import com.google.gson.annotations.SerializedName

data class OTPLoginDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("password") val password: String?,
)
