package com.example.toastgoand.home.directtalk.network

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

interface NewDirectFrameApiInterface {
    @Headers("Content-Type: application/json")
    @POST("clubs/create_frames_one_on_one/")
    suspend fun startNewDirectFrame(@Body frameInfo: NewDirectFrameDataClass): NewDirectFrameDataClass
}

object NewDirectFrameApi {
    val retrofitService: NewDirectFrameApiInterface by lazy {
        retrofit.create(NewDirectFrameApiInterface::class.java)
    }
}