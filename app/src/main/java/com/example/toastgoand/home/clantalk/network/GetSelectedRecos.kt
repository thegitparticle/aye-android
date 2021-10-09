package com.example.toastgoand.home.clantalk.network

import com.example.toastgoand.network.defaultrecos.DefaultRecosDataClass
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

interface GetSelectedRecosInterface {
    @GET("users/recommend_images/{userid}/{word}/False/")
    suspend fun getSelectedWordRecos(
        @Path("userid") userid: String,
        @Path("word") word: String
    ): MutableList<GetSelectedRecosDataClass>
}

object GetSelectedRecos {
    val retrofitService: GetSelectedRecosInterface by lazy {
        retrofit.create(GetSelectedRecosInterface::class.java)
    }
}