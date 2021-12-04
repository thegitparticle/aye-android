package com.example.toastgoand.home.directs

import androidx.annotation.Keep
import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon
import kotlinx.serialization.Serializable

private val klaxon = Klaxon()

@Keep
@Serializable
data class NudgeItemDataClass (
    val name: String,
    val id: Int,

    @Json(name = "profile_pic")
    val profilePic: String
) {
    public fun toJson() = klaxon.toJsonString(this)

    companion object {
        public fun fromJson(json: String) = klaxon.parse<NudgeItemDataClass>(json)
    }
}