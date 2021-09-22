package com.example.toastgoand.home.otherprofile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.home.clanhub.ClanDetailsDataClass
import com.example.toastgoand.home.clanhub.network.ClanDetailsApi
import com.example.toastgoand.home.otherprofile.network.OtherProfileApi
import kotlinx.coroutines.launch

class OtherProfileViewModel: ViewModel() {

    private val _otherProfile = MutableLiveData<List<OtherProfileDataClass>>()
    val otherProfile: LiveData<List<OtherProfileDataClass>>
        get() = _otherProfile


    fun getOtherProfileHere(otheruserid: Int) {
        viewModelScope.launch {
            try {
                val userResult = OtherProfileApi.retrofitService.getOtherProfile("", otheruserid.toString())
                var x_here: List<OtherProfileDataClass> = userResult
                _otherProfile.value = x_here
                Log.i("otherprofileviewing", _otherProfile.value!!.toString())
            } catch (e: Exception) {
                Log.i("otherprofileviewing", "API call for user details, Failed! ${e.message}")
            }
        }
    }

}