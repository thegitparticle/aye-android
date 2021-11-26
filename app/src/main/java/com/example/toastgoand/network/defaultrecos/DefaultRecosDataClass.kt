package com.example.toastgoand.network.defaultrecos

import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "defaultRecosTable"
)

@Keep
@Serializable
data class DefaultRecosDataClass(
    @PrimaryKey val id: Int,
    @Serializable val links: List<String>
)


//@Serializable
//data class DefaultRecosDataClass(
//
//    @PrimaryKey val a: String,
//    val b: String,
//    val c: String,
//    val d: String,
//    val e: String,
//    val f: String,
//    val g: String
//)

