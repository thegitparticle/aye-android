package com.example.toastgoand.home.myprofile.network

import com.google.gson.annotations.SerializedName
import java.io.File

data class EditProfileDataClass(
    @SerializedName("bio") val bio: String?,
    @SerializedName("image") val image: File
)
