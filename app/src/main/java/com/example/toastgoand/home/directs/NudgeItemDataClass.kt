package com.example.toastgoand.home.directs

import com.beust.klaxon.Json
import com.beust.klaxon.Klaxon

private val klaxon = Klaxon()

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