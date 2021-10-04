package com.example.toastgoand.auth.otplogin

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.auth.otplogin.network.OTPLoginApi
import com.example.toastgoand.auth.otplogin.network.OTPLoginDataClass
import com.example.toastgoand.home.startclan.network.StartClanApi
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import com.example.toastgoand.network.phonecheck.PhoneCheckDataClass
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import kotlinx.coroutines.launch

class OtpLoginViewModel(): ViewModel() {


    private val _otpCheck = MutableLiveData<String>()
    val otpCheck: LiveData<String>
        get() = _otpCheck

    private val _deets = MutableLiveData<UserDetailsDataClass>()
    val deets: LiveData<UserDetailsDataClass>
        get() = _deets

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun sendOtpPayload(payload: OTPLoginDataClass) {
        viewModelScope.launch {
            try {
                val responseOTPHere = OTPLoginApi.retrofitService.otpLoginSend(payload)
                _otpCheck.value = "worked"
                Log.i("startclanlog", "otp worked")

            } catch (e: Exception) {
                _otpCheck.value = "failed"
                Log.i("startclanlog", e.toString())
            }
        }
    }

    fun getUserDetailsHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = UserDetailsApi.retrofitService.getUserDetails(phone)
                _deets.value = userResult
                Log.i("OtpSignUpViewModel", _deets.value.toString())
            } catch (e: Exception) {
                Log.i("OtpSignUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}