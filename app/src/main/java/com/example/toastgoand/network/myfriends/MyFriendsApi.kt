package com.example.toastgoand.network.myfriends

import com.example.toastgoand.network.directs.MyDirectsApiInterface
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

interface MyFriendsApiInterface {
    @GET("users/fetch_friends_list/{userid}/")
    suspend fun getMyFriends(@Path("userid") userid: String): List<MyFriendsDataClass>
}

object MyFriendsApi {
    val retrofitService: MyFriendsApiInterface by lazy {
        retrofit.create(MyFriendsApiInterface::class.java)
    }
}