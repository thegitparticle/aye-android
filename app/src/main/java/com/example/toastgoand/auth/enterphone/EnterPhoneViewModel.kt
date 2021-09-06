package com.example.toastgoand.auth.enterphone

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import com.example.toastgoand.network.phonecheck.PhoneCheckDataClass
import kotlinx.coroutines.launch

class EnterPhoneViewModel: ViewModel() {

    private val _phoneCheck = MutableLiveData<String>()
    val phoneCheck: LiveData<String>
        get() = _phoneCheck

    private val _countryCode = MutableLiveData<String>()
    val countryCode: LiveData<String>
        get() = _countryCode

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun checkPhoneNumberHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = PhoneCheckApi.retrofitService.checkPhone(phone)
//                Log.i("EnterPhoneViewModel", userResult.toString())
                var x_here: PhoneCheckDataClass = userResult
                _phoneCheck.value = x_here?.user_exists
                Log.i("EnterPhoneViewModel", _phoneCheck.value!!)
            } catch (e: Exception) {
                Log.i("EnterPhoneViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}