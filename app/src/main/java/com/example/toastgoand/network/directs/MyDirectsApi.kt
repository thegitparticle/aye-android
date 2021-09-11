package com.example.toastgoand.network.directs

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
        .baseUrl(com.example.toastgoand.network.directs.BASE_URL)
        .build()

interface MyDirectsApiInterface {
    @GET("users/my_directs/{userid}")
    suspend fun getMyDirects(@Path("userid") userid: String): List<MyDirectsDataClass>
}

object MyDirectsApi {
    val retrofitService: MyDirectsApiInterface by lazy {
        retrofit.create(MyDirectsApiInterface::class.java)
    }
}