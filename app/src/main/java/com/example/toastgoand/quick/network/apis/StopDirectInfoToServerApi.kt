package com.example.toastgoand.quick.network.apis

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

interface StopDirectInfoToServerApiInterface {
    @GET("users/stop_direct_stream/{userid}/{channelid}")
    suspend fun stopDirectStream(
        @Path("userid") userid: String,
        @Path("channelid") channelid: String
    )
}

object StopDirectInfoToServerApi {
    val retrofitService: StopDirectInfoToServerApiInterface by lazy {
        retrofit.create(StopDirectInfoToServerApiInterface::class.java)
    }
}