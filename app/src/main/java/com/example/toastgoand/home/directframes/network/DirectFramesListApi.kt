package com.example.toastgoand.home.directframes.network

import com.example.toastgoand.home.directframes.DirectFrameDataClass
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

interface DirectFrameListApiInterface {
    @GET("clubs/frames_one_on_one_filtered/2021/{month}/{directid}/")
    suspend fun getMonthlyFramesList(
        @Path("month") month: String,
        @Path("directid") directid: String
    ): List<DirectFrameDataClass>
}

object DirectFramesListApi {
    val retrofitService: DirectFrameListApiInterface by lazy {
        retrofit.create(DirectFrameListApiInterface::class.java)
    }
}