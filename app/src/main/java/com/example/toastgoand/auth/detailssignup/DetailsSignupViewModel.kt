package com.example.toastgoand.auth.detailssignup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.auth.network.DetailsSignUpApi
import com.example.toastgoand.auth.network.dataclasses.DetailsSignUpDataClass
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import kotlinx.coroutines.launch

class DetailsSignupViewModel : ViewModel() {

    private val _detailsposted = MutableLiveData<Boolean>()
    val detailsposted: LiveData<Boolean>
        get() = _detailsposted

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun sendDetailsOfNewUser(payloadHere: DetailsSignUpDataClass) {
        viewModelScope.launch {
            try {
                Log.i("signupdebug", "composable scope is lauched")
                DetailsSignUpApi.retrofitService.newUserDetails(
                    data = payloadHere
                )
                _detailsposted.value = true
                Log.i(
                    "signupdebug",
                    "composable scope inside after api call"
                )
            } catch (e: Exception) {
                Log.i(
                    "signupdebug",
                    e.toString()
                )
            }
        }
    }

    fun getUserDetailsHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = PhoneCheckApi.retrofitService.checkPhone(phone)
            } catch (e: Exception) {
                Log.i("EnterPhoneViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}