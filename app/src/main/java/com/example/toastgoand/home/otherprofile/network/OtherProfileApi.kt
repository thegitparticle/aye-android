package com.example.toastgoand.home.otherprofile.network

import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
import com.example.toastgoand.home.otherprofile.OtherProfileDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface OtherProfileApiInterface {
    @GET("users/profile-update/")
    suspend fun getOtherProfile(@Query("id") id: String, @Query("user") otheruserid: String): List<OtherProfileDataClass>
}

object OtherProfileApi {
    val retrofitService: OtherProfileApiInterface by lazy {
        retrofit.create(OtherProfileApiInterface::class.java)
    }
}
