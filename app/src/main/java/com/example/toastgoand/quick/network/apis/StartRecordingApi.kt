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

interface StartRecordingApiInterface {
    @GET("users/agora_start_composite_recording/{userid}/{channelid}")
    suspend fun getTheToken(
        @Path("userid") userid: String,
        @Path("channelid") channelid: String
    ): StartRecordingResponseDataClass
}

object StartRecordingApi {
    val retrofitService: StartRecordingApiInterface by lazy {
        retrofit.create(StartRecordingApiInterface::class.java)
    }
}