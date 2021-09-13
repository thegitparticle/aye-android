package com.example.toastgoand.home.clanframes.network

import com.example.toastgoand.home.clanframes.ClanFrameDataClass
import com.example.toastgoand.home.directframes.DirectFrameDataClass
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

interface ClanFrameListApiInterface {
    @GET("clubs/frames_clubs_filter/2021/{month}/{channelid}/")
    suspend fun getMonthlyFramesList(
        @Path("month") month: String,
        @Path("channelid") channelid: String
    ): List<ClanFrameDataClass>
}

object ClanFramesListApi {
    val retrofitService: ClanFrameListApiInterface by lazy {
        retrofit.create(ClanFrameListApiInterface::class.java)
    }
}