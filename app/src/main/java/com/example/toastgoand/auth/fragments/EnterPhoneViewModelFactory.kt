package com.example.toastgoand.auth.fragments

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.network.userdetails.UserDetailsApi
import kotlinx.coroutines.launch

class EnterPhoneViewModelFactory (private val finalName: String) : ViewModelProvider.Factory {

    override fun <T: ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EnterPhoneViewModel::class.java)) {
//            val userResult = UserDetailsApi.retrofitService.getUserDetails().toString()
            return EnterPhoneViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}