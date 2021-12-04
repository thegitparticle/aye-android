package com.example.toastgoand.auth.network.dataclasses

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class UploadContactsDataClass(
    @SerializedName("contact_list") var contact_list: String?,
)

@Keep
@Serializable
data class CountryCodeDataClass(
    @SerializedName("country_code") val country_code: String?,
)
