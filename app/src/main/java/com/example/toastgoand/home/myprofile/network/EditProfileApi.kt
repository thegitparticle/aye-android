package com.example.toastgoand.home.myprofile.network

import com.example.toastgoand.home.otherprofile.OtherProfileDataClass
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.*
import okhttp3.MultipartBody

import okhttp3.RequestBody

import okhttp3.ResponseBody
import retrofit2.Call


private const val BASE_URL = "https://apisayepirates.life/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit =
    Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface EditProfileApiInterface {
    @Multipart
    @Headers("Content-Type: application/json")
    @PUT("users/profile-update/{profileupdateid}/")

//    open fun uploadFile(
//        @Part("bio") bio: RequestBody?,
//        @Part image: Part?
//    ): Call<ResponseBody?>?

    suspend fun setNewProfile(@Path("profileupdateid") profileupdateid: String, @Part("bio") bio: RequestBody?,
                              @Part image: MultipartBody.Part?)
}

object EditProfileApi {
    val retrofitService: EditProfileApiInterface by lazy {
        retrofit.create(EditProfileApiInterface::class.java)
    }
}