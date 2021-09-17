package com.example.toastgoand.network.defaultrecos

import com.example.toastgoand.network.directs.MyDirectsDataClass
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

interface DefaultRecosApiInterface {
    @GET("users/recommend_images/{userid}/fun/False/")
    suspend fun getDefaultRecos(@Path("userid") userid: String): List<DefaultRecosDataClass>
}

object DefaultRecosApi {
    val retrofitService: DefaultRecosApiInterface by lazy {
        retrofit.create(DefaultRecosApiInterface::class.java)
    }
}