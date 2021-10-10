package com.example.toastgoand.quick.network.apis

import com.example.toastgoand.quick.network.dataclasses.StartRecordingResponseDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface StartClanInfoToServerApiInterface {
    @GET("users/start_club_stream/{userid}/{clubid}")
    suspend fun startClanStream(
        @Path("userid") userid: String,
        @Path("clubid") clubid: String
    )
}

object StartClanInfoToServerApi {
    val retrofitService: StartClanInfoToServerApiInterface by lazy {
        retrofit.create(StartClanInfoToServerApiInterface::class.java)
    }
}