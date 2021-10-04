package com.example.toastgoand.auth.otpsignup

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.auth.loginsetup.LoginSetupViewModel
import com.example.toastgoand.auth.network.CheckInvitedOrNotApi
import com.example.toastgoand.auth.network.dataclasses.CheckInvitedOrNotDataClass
import com.example.toastgoand.network.phonecheck.PhoneCheckApi
import com.example.toastgoand.network.phonecheck.PhoneCheckDataClass
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class OtpSignupViewModel(private val repo: UserDetailsRepo): ViewModel() {

    val userDetails: LiveData<UserDetailsDataClass> = repo.userDetails.asLiveData()

    private val _invited = MutableLiveData<String>()
    val invited: LiveData<String>
        get() = _invited

    private val _invitedData = MutableLiveData<CheckInvitedOrNotDataClass>()
    val invitedData: LiveData<CheckInvitedOrNotDataClass>
        get() = _invitedData

    override fun onCleared() {
        super.onCleared()
        Log.i("OtpSignUpViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun insert(userDetails: UserDetailsDataClass) = viewModelScope.launch {
        repo.insert(userDetails)
    }

    fun getUserDetailsHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = UserDetailsApi.retrofitService.getUserDetails(phone)
                var x_here: UserDetailsDataClass = userResult
                insert(x_here)
                repo.insert(x_here)
                Log.i("OtpSignUpViewModel", x_here.toString())
            } catch (e: Exception) {
                Log.i("OtpSignUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }

    fun checkInvitedOrNot(phone: String) {
        viewModelScope.launch {
            try {
                val invitedValue = CheckInvitedOrNotApi.retrofitService.invitedOrNotCheck(phone = phone)
                var x_here: CheckInvitedOrNotDataClass = invitedValue
                _invitedData.value = x_here
                _invited.value = x_here?.invited_by_user!!
                Log.i("OtpSignUpViewModel", _invited.value!!)
            } catch (e: Exception) {
                Log.i("OtpSignUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class OtpSignupViewModelFactory(private val repo: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OtpSignupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OtpSignupViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}