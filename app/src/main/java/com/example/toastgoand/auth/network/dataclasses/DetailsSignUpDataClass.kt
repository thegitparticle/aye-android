package com.example.toastgoand.auth.network.dataclasses

import com.google.gson.annotations.SerializedName
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

data class DetailsSignUpDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("profile") val profile: Map<String, String>,
)
