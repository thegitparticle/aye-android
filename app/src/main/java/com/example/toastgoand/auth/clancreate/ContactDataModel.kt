package com.example.toastgoand.auth.clancreate

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ContactDataModel(
    val name: Int,
    val phone: String
)