package com.example.toastgoand.home.talkthings.network

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

interface GetSelectedRecosApiInterface {
    @GET("users/recommend_images/{userid}/{word}/False")
    suspend fun getSelectedRecos(@Path("userid") userid: String, @Path("word") word: String)
}

object GetSelectedRecosApi {
    val retrofitService: GetSelectedRecosApiInterface by lazy {
        retrofit.create(GetSelectedRecosApiInterface::class.java)
    }
}