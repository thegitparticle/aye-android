package com.example.toastgoand.home.invitepeopledirectly.network

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ContactsListItemDataClass(
    val name: String,
    val phone: String,
)
