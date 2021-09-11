package com.example.toastgoand.home.clans

import android.app.PendingIntent.getActivity
import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.myclans.MyClansApi
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.myclans.MyClansRepo
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class ClansViewModel(private val repo: MyClansRepo, private val repoDeets: UserDetailsRepo) : ViewModel() {

    val myClans: LiveData<List<MyClansDataClass>> = repo.myClans.asLiveData()

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    private fun insert(myClans: List<MyClansDataClass>) = viewModelScope.launch {
        repo.insert(myClans)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getMyClansHere(it.id) }
        }
    }

    fun getMyClansHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myClansResult = MyClansApi.retrofitService.getMyClans(userid.toString())
                var x_here: List<MyClansDataClass> = myClansResult
                insert(x_here)
//                repo.insert(x_here)
                Log.i("SettingUpViewModel", x_here.toString())
            } catch (e: Exception) {
                Log.i("SettingUpViewModel", "API call for user details, Failed! ${e.message}")
            }
        }
    }
}

class ClansViewModelFactory (private val repo: MyClansRepo, private val repoDeets: UserDetailsRepo) : ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClansViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ClansViewModel(repo, repoDeets) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}