package com.example.toastgoand.auth.otplogin

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import com.example.toastgoand.network.phonecheck.PhoneCheckDataClass
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class OtpLoginViewModel(): ViewModel() {


    private val _otpCheck = MutableLiveData<String>()
    val otpCheck: LiveData<String>
        get() = _otpCheck

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun getUserDetailsHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = PhoneCheckApi.retrofitService.checkPhone(phone)
                var x_here: PhoneCheckDataClass = userResult
                _otpCheck.value = x_here?.user_exists
                Log.i("EnterPhoneViewModel", _otpCheck.value!!)
            } catch (e: Exception) {
                Log.i("EnterPhoneViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}