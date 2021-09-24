package com.example.toastgoand.home

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.home.directs.DirectsViewModel
import com.example.toastgoand.network.directs.MyDirectsRepo
import com.example.toastgoand.network.nudgelist.NudgeToApi
import com.example.toastgoand.network.nudgelist.NudgeToDataClass
import com.example.toastgoand.network.nudgelist.NudgeToRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch

class LandingViewModel(private val repoDeets: UserDetailsRepo): ViewModel() {

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

}

class LandingViewModelFactory (private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LandingViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LandingViewModel(repoDeets = repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}