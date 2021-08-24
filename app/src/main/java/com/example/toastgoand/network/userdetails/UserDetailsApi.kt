package com.example.toastgoand.network.userdetails

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(BASE_URL)
        .build()

interface UserDetailsApiInterface {
    @GET("users/my_clubs/82/")
    suspend fun getUserDetails(): List<UserDetailsDataClass>
}

object UserDetailsApi {
    val retrofitService: UserDetailsApiInterface by lazy {
        retrofit.create(UserDetailsApiInterface::class.java)
    }
}