package com.example.toastgoand.auth.settingup

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class SettingUpViewModel(private val repo: UserDetailsRepo): ViewModel() {

    val userDetails: LiveData<UserDetailsDataClass> = repo.userDetails.asLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.i("QuoteFragmentViewModel", "QuoteFragmentViewModel destroyed")
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
            } catch (e: Exception) {
                Log.i("SettingUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}


class SettingUpViewModelFactory(private val repo: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingUpViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SettingUpViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}