package com.example.toastgoand.auth.network

import com.example.toastgoand.auth.network.dataclasses.OtpSignUpDataClass
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

interface OtpSignUpApiInterface {
    @Headers("Content-Type: application/json")
    @POST("auth/token/")
    suspend fun newUserOtpCheck(@Body data: OtpSignUpDataClass): OtpSignUpDataClass
}

object OtpSignUpApi {
    val retrofitService: OtpSignUpApiInterface by lazy {
        retrofit.create(OtpSignUpApiInterface::class.java)
    }
}