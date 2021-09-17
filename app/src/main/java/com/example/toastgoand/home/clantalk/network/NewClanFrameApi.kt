package com.example.toastgoand.home.clantalk.network

import com.example.toastgoand.home.clanframes.ClanFrameDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface NewClanFrameApiInterface {
    @Headers("Content-Type: application/json")
    @POST("clubs/create_frames_clubs/")
    suspend fun startNewClanFrame(@Body frameInfo: NewClanFrameDataClass): NewClanFrameDataClass
}

object NewClanFrameApi {
    val retrofitService: NewClanFrameApiInterface by lazy {
        retrofit.create(NewClanFrameApiInterface::class.java)
    }
}