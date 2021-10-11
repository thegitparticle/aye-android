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

interface StopClanInfoToServerApiInterface {
    @GET("users/stop_club_stream/{userid}/{clubid}")
    suspend fun stopClanStream(
        @Path("userid") userid: String,
        @Path("clubid") clubid: String
    )
}

object StopClanInfoToServerApi {
    val retrofitService: StopClanInfoToServerApiInterface by lazy {
        retrofit.create(StopClanInfoToServerApiInterface::class.java)
    }
}
