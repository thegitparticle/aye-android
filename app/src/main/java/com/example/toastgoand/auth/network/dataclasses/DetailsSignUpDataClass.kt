package com.example.toastgoand.auth.network.dataclasses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@Keep
@Serializable
data class DetailsSignUpDataClass(
    @SerializedName("phone") val phone: String?,
    @SerializedName("full_name") val full_name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("profile") val profile: Map<String, String>,
)
