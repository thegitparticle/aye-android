package com.example.toastgoand.auth.clancreate

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toastgoand.home.clanhub.network.InviteToClanAddApi
import com.example.toastgoand.home.clanhub.network.InviteToClanSendApi
import com.example.toastgoand.home.invitepeopledirectly.network.GetContactsListApi
import kotlinx.coroutines.launch

class NameClanViewModel : ViewModel() {

    fun invitePeopleToClub(phone: String, clubid: String, hostuserid: String) {
        viewModelScope.launch {
            try {
                InviteToClanSendApi.retrofitService.inviteClanSendToClan(
                    phonenumber = phone,
                    userid = hostuserid.toString()
                )
                Log.i("invitepersonlogs", "send sms to invite")
                InviteToClanAddApi.retrofitService.inviteClanAddToClan(
                    phonenumber = phone,
                    userid = hostuserid.toString(),
                    clubid = clubid.toString()
                )
            } catch (e: Exception) {
                Log.i("invitepersonlogs", e.toString())
            }
        }
    }

}