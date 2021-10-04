package com.example.toastgoand.auth.network.apis

import com.example.toastgoand.auth.network.dataclasses.UploadContactsDataClass
import com.example.toastgoand.home.myprofile.network.EditProfileDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*

private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface UploadContactsApiInterface {
    @PUT("users/post_contacts_to_server/{userid}/")
    suspend fun uploadContacts(@Path("userid") userid: String, @Body data: UploadContactsDataClass)
}

object UploadContactsApi {
    val retrofitService: UploadContactsApiInterface by lazy {
        retrofit.create(UploadContactsApiInterface::class.java)
    }
}