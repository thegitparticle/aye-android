package com.example.toastgoand.home.directs.network

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

interface NudgeToStartDirectApiInterface {
    @GET("users/start_chat/{mainuserid}/{otheruserid}/{directid}")
    suspend fun startANewDirect(
        @Path("mainuserid") mainuserid: String,
        @Path("otheruserid") otheruserid: String,
        @Path("directid") directid: String
    )
}

object NudgeToStartDirectApi {
    val retrofitService: NudgeToStartDirectApiInterface by lazy {
        retrofit.create(NudgeToStartDirectApiInterface::class.java)
    }
}
