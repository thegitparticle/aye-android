package com.example.toastgoand.home.invitepeopledirectly.network

import com.example.toastgoand.network.myfriends.MyFriendsDataClass
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

interface GetContactsListApiInterface {
    @GET("users/user_separate_contact_list/{userid}/")
    suspend fun getMyFriends(@Path("userid") userid: String): List<ContactsListItemDataClass>
}

object GetContactsListApi {
    val retrofitService: GetContactsListApiInterface by lazy {
        retrofit.create(GetContactsListApiInterface::class.java)
    }
}