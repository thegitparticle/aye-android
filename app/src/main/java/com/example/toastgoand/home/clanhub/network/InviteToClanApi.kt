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

interface InviteToClanSendApiInterface {
    @GET("users/send_invite/{phonenumber}/{userid}")
    suspend fun inviteClanSendToClan(@Path("phonenumber") phonenumber: String, @Path("userid") userid: String)
}

object InviteToClanSendApi {
    val retrofitService: InviteToClanSendApiInterface by lazy {
        retrofit.create(InviteToClanSendApiInterface::class.java)
    }
}

interface InviteToClanAddApiInterface {
    @GET("users/add_invited_user/{phonenumber}/{userid}/{clubid}")
    suspend fun inviteClanAddToClan(@Path("phonenumber") phonenumber: String, @Path("userid") userid: String, @Path("clubid") clubid: String)
}

object InviteToClanAddApi {
    val retrofitService: InviteToClanAddApiInterface by lazy {
        retrofit.create(InviteToClanAddApiInterface::class.java)
    }
}