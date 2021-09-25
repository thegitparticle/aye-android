package com.example.toastgoand.auth.detailssignup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import kotlinx.coroutines.launch

class DetailsSignupViewModel: ViewModel() {

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
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