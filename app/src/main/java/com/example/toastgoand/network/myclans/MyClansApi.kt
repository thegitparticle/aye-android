package com.example.toastgoand.network.myclans

import com.example.toastgoand.network.userdetails.UserDetailsDataClass
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
        .baseUrl(com.example.toastgoand.network.myclans.BASE_URL)
        .build()

interface MyClansApiInterface {
    @GET("users/my_clubs/{userid}/")
    suspend fun getMyClans(@Path("userid") userid: String): List<MyClansDataClass>
}

object MyClansApi {
    val retrofitService: MyClansApiInterface by lazy {
        retrofit.create(MyClansApiInterface::class.java)
    }
}