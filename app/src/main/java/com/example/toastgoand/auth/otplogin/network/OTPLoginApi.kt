package com.example.toastgoand.auth.otplogin.network

import com.example.toastgoand.home.startclan.network.StartClanDataClass
import com.example.toastgoand.home.startclan.network.StartClanResponseDataClass
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

interface OTPLoginApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/token/")
    suspend fun otpLoginSend(@Body data: OTPLoginDataClass)
}

object OTPLoginApi {
    val retrofitService: OTPLoginApiInterface by lazy {
        retrofit.create(OTPLoginApiInterface::class.java)
    }
}