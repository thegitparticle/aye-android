package com.example.toastgoand.auth.network.dataclasses

import com.google.gson.annotations.SerializedName

data class UploadContactsDataClass(
    @SerializedName("contact_list") var contact_list: String?,
)

data class CountryCodeDataClass(
    @SerializedName("country_code") val country_code: String?,
)
