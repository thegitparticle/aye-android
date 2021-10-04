package com.example.toastgoand.auth.network

import com.example.toastgoand.auth.network.dataclasses.CheckInvitedOrNotDataClass
import com.example.toastgoand.auth.network.dataclasses.OtpSignUpDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
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

interface CheckInvitedOrNotApiInterface {
    @GET("users/invited_or_not_api/{phone}/")
    suspend fun invitedOrNotCheck(@Path("phone") phone: String): CheckInvitedOrNotDataClass
}

object CheckInvitedOrNotApi {
    val retrofitService: CheckInvitedOrNotApiInterface by lazy {
        retrofit.create(CheckInvitedOrNotApiInterface::class.java)
    }
}