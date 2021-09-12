package com.example.toastgoand.network.nudgelist

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
        .baseUrl(com.example.toastgoand.network.nudgelist.BASE_URL)
        .build()


interface NudgeToApiInterface {
    @GET("users/friends_but_not_friends/{userid}/")
    suspend fun getNudgeTo(@Path("userid") userid: String): List<NudgeToDataClass>
}

object NudgeToApi {
    val retrofitService: NudgeToApiInterface by lazy {
        retrofit.create(NudgeToApiInterface::class.java)
    }
}
