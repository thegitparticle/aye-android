package com.example.toastgoand.auth.settingup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import com.example.toastgoand.network.phonecheck.PhoneCheckDataClass
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import kotlinx.coroutines.launch

class SettingUpViewModel: ViewModel() {
    private val _myUserName = MutableLiveData<String>()
    val myUserName: LiveData<String>
        get() = _myUserName

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun getUserDetailsHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = UserDetailsApi.retrofitService.getUserDetails(phone)
//                Log.i("EnterPhoneViewModel", userResult.toString())
                var x_here: UserDetailsDataClass = userResult
                _myUserName.value = x_here.user.username
                Log.i("SettingUpViewModel", _myUserName.value!!)
            } catch (e: Exception) {
                Log.i("SettingUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}