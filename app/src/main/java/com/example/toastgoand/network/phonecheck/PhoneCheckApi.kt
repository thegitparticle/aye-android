package com.example.toastgoand.network.phonecheck

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

private val retrofit =
    Retrofit.Builder().addConverterFactory(MoshiConverterFactory.create(moshi)).baseUrl(
        BASE_URL
    ).build()

interface PhoneCheckApiInterface {
    @GET("users/send_otp/{phone}")
    suspend fun checkPhone(@Path("phone") phone: String): PhoneCheckDataClass
}

object PhoneCheckApi {
    val retrofitService: PhoneCheckApiInterface by lazy {
        retrofit.create(PhoneCheckApiInterface::class.java)
    }
}








//
//val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
//    level = HttpLoggingInterceptor.Level.BODY
//}
//
//val client: OkHttpClient = OkHttpClient.Builder().apply {
//    addInterceptor(com.example.toastgoand.network.userdetails.interceptor)
//}.build()