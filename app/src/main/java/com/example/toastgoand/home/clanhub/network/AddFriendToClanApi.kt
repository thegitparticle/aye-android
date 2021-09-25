package com.example.toastgoand.home.clanhub.network

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

interface AddFriendToClanApiInterface {
    @GET("users/add_users_to_club/{adduserid}/{clubid}")
    suspend fun addFriendToClan(@Path("adduserid") adduserid: String, @Path("clubid") clubid: String)
}

object AddFriendToClanApi {
    val retrofitService: AddFriendToClanApiInterface by lazy {
        retrofit.create(AddFriendToClanApiInterface::class.java)
    }
}
