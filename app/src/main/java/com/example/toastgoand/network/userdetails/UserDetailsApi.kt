package com.example.toastgoand.network.userdetails

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://apisayepirates.life/api/"
//private const val BASE_URL = "https://run.mocky.io/v3/"
//private const val BASE_URL = "https://android-kotlin-fun-mars-server.appspot.com"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
    level = HttpLoggingInterceptor.Level.BODY
}

val client: OkHttpClient = OkHttpClient.Builder().apply {
    addInterceptor(interceptor)
}.build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
//        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl(BASE_URL)
        .client(client)
        .build()

interface UserDetailsApiInterface {
    //    @GET("a97f111d-e6bc-4875-9c2f-a3da6b809a93")
//    @GET("users/my_clubs/82/")
//    @GET("realestate")

    @GET("users/profile/+919849167641/")

//    suspend fun getUserDetails(): List<UserDetailsDataClass>
    suspend fun getUserDetails(): Map<Any,Any>
//    suspend fun getUserDetails(): Call<String>
}

object UserDetailsApi {
    val retrofitService: UserDetailsApiInterface by lazy {
        retrofit.create(UserDetailsApiInterface::class.java)
    }
}