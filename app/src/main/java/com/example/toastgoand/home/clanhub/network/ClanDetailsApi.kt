package com.example.toastgoand.home.clanhub.network

import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
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

interface ClanDetailsApiInterface {
    @GET("clubs/{clubid}/")
    suspend fun getUserDetails(@Path("clubid") phone: String): ClanDetailsDataClass
}

object ClanDetailsApi {
    val retrofitService: ClanDetailsApiInterface by lazy {
        retrofit.create(ClanDetailsApiInterface::class.java)
    }
}
