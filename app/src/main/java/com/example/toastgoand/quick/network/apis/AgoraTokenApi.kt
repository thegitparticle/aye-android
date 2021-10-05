package com.example.toastgoand.quick.network.apis

import com.example.toastgoand.network.myclans.MyClansDataClass
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

interface AgoraTokenApiInterface {
    @GET("users/agora_token_generator/{userid}/{channelid}")
    suspend fun getTheToken(
        @Path("userid") userid: String,
        @Path("channelid") channelid: String
    ): String
}

object AgoraTokenApi {
    val retrofitService: AgoraTokenApiInterface by lazy {
        retrofit.create(AgoraTokenApiInterface::class.java)
    }
}