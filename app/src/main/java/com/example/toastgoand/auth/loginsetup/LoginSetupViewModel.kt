package com.example.toastgoand.auth.loginsetup

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class LoginSetupViewModel(private val repo: UserDetailsRepo): ViewModel() {

    val userDetails: LiveData<UserDetailsDataClass> = repo.userDetails.asLiveData()

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginSetupViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun insert(userDetails: UserDetailsDataClass) = viewModelScope.launch {
        repo.insert(userDetails)
    }

    fun getUserDetailsWhileLoginHere(phone: String) {
        viewModelScope.launch {
            try {
                val userResult = UserDetailsApi.retrofitService.getUserDetails(phone)
                var x_here: UserDetailsDataClass = userResult
                insert(x_here)
                repo.insert(x_here)
                Log.i("observer", x_here.toString())
            } catch (e: Exception) {
                Log.i("LoginSetupViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class LoginSetupViewModelFactory(private val repo: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginSetupViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LoginSetupViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}