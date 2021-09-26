package com.example.toastgoand.home.startclan.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface StartClanApiInterface {
    @Headers("Content-Type: application/json")
    @POST("clubs/create_club/")
    suspend fun startClan(@Body data: StartClanDataClass): StartClanDataClass
}

object StartClanApi {
    val retrofitService: StartClanApiInterface by lazy {
        retrofit.create(StartClanApiInterface::class.java)
    }
}