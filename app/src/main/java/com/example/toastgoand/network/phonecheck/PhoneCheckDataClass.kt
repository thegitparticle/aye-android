package com.example.toastgoand.network.phonecheck

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class PhoneCheckDataClass(
    val user_exists: String
)