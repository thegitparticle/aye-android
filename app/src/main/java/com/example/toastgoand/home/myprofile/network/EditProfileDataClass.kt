package com.example.toastgoand.home.myprofile.network

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable
import java.io.File

@Keep
data class EditProfileDataClass(
    @SerializedName("bio") val bio: String?,
    @SerializedName("image") val image: File
)
