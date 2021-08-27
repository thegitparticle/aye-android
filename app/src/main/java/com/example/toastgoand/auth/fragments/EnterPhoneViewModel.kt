package com.example.toastgoand.auth.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.network.userdetails.UserDetailsApi
import kotlinx.coroutines.launch
import java.util.*
import javax.security.auth.callback.Callback

class EnterPhoneViewModel(): ViewModel() {

    private val _response = MutableLiveData<MutableMap<String, Any>>()
    val response: LiveData<MutableMap<String, Any>>
        get() = _response

    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String>
        get() = _userName

    override fun onCleared() {
        super.onCleared()
        Log.i("EnterPhoneViewModel", "enter phone view model destroyed")
    }

    init {
        getUserDetails()
    }


    private fun getUserDetails() {
        viewModelScope.launch{
            try {
                val userResult = UserDetailsApi.retrofitService.getUserDetails()
                _userName.value = "failed user"
                Log.i("EnterPhoneModelAPIWorked", userResult.toString())
            }
            catch (e: Exception) {
                _userName.value = "failed user"
                Log.i("EnterPhoneModelFailed", e.toString())
            }
        }
    }

}
