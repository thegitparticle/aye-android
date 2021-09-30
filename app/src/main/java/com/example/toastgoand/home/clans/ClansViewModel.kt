package com.example.toastgoand.home.clans

import android.util.Log
import androidx.lifecycle.*
import com.example.toastgoand.network.myclans.MyClansApi
import com.example.toastgoand.network.myclans.MyClansDataClass
import com.example.toastgoand.network.myclans.MyClansRepo
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClansViewModel(private val repo: MyClansRepo, private val repoDeets: UserDetailsRepo) : ViewModel() {

    private val _isRefreshing = MutableStateFlow(false)

    val isRefreshing: StateFlow<Boolean>
        get() = _isRefreshing.asStateFlow()

    fun refresh() {
        // This doesn't handle multiple 'refreshing' tasks, don't use this
        viewModelScope.launch {
            _isRefreshing.emit(true)
            deets.value?.user?.let { getMyClansHere(it.id) }
            delay(2000)
            _isRefreshing.emit(false)
        }
    }

    val myClans: LiveData<List<MyClansDataClass>> = repo.myClans.asLiveData()

    private val _liveClans = MutableLiveData<MutableList<MyClansDataClass>>()
    val liveClans: LiveData<MutableList<MyClansDataClass>>
        get() = _liveClans

    val deets: LiveData<UserDetailsDataClass> = repoDeets.userDetails.asLiveData()

    private fun insert(myClans: List<MyClansDataClass>) = viewModelScope.launch {
        repo.insert(myClans)
    }

    init {
        deets.observeForever {
            deets.value?.user?.let { getMyClansHere(it.id) }
        }
        myClans.observeForever {
            val dummyHere = mutableListOf<MyClansDataClass>()
            for (item in myClans.value!!) {
                if (item.ongoing_frame) {
                    dummyHere.add(item)
                }
            }
            _liveClans.value = dummyHere
        }
        _liveClans.observeForever {
            Log.i("liveclansif", _liveClans.toString())
        }
    }

    fun getMyClansHere(userid: Int) {
        viewModelScope.launch {
            try {
                val myClansResult = MyClansApi.retrofitService.getMyClans(userid.toString())
                var x_here: List<MyClansDataClass> = myClansResult
                insert(x_here)
                Log.i("speed clans api call", x_here.size.toString())
                Log.i("speed clans api call", x_here.toString())
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