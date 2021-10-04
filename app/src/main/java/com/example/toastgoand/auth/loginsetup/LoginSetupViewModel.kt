package com.example.toastgoand.auth.loginsetup

import android.app.PendingIntent.getActivity
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.alexstyl.contactstore.ContactStore
import com.deepakkumardk.kontactpickerlib.KontactPicker
import com.deepakkumardk.kontactpickerlib.model.MyContacts
import com.example.toastgoand.auth.network.apis.UploadContactsApi
import com.example.toastgoand.auth.network.dataclasses.CountryCodeDataClass
import com.example.toastgoand.auth.network.dataclasses.UploadContactsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsApi
import com.example.toastgoand.network.userdetails.UserDetailsDataClass
import com.example.toastgoand.network.userdetails.UserDetailsRepo
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class LoginSetupViewModel(private val repo: UserDetailsRepo): ViewModel() {

    val userDetails: LiveData<UserDetailsDataClass> = repo.userDetails.asLiveData()

    private lateinit var mContext: Context

    private val _uploaded = MutableLiveData<Boolean>(false)
    val uploaded: LiveData<Boolean>
        get() = _uploaded

    override fun onCleared() {
        super.onCleared()
        Log.i("LoginSetupViewModel", "QuoteFragmentViewModel destroyed")
    }

    fun insert(userDetails: UserDetailsDataClass) = viewModelScope.launch {
        repo.insert(userDetails)
    }

    @InternalCoroutinesApi
    fun uploadUserContacts(userid: String, countryIndicator: String, list: MutableList<Map<String, String>>) {
        Log.i("settingupdebug", "upload contacts function in vm called")
        viewModelScope.launch {
            try {
                var payload: UploadContactsDataClass = UploadContactsDataClass(contact_list = "")
                var countryCode = mapOf("country_code" to countryIndicator)

                list.add(0, countryCode)
                payload.contact_list = Json.encodeToString(list)
                val contactsSuccess = UploadContactsApi.retrofitService.uploadContacts(userid = userid, data = payload)
                _uploaded.value = true

            } catch (e: Exception) {
                _uploaded.value = false
                Log.i("settingupdebug", "upload contacts PUT call for user details, Failed! ${e.message}")
            }
        }
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