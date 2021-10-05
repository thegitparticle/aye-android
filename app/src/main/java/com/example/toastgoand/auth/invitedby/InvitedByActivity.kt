package com.example.toastgoand.auth.invitedby

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.beust.klaxon.Parser
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.strangerintro.StrangerIntroActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityInvitedByBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper
import kotlinx.serialization.json.Json

class InvitedByActivity : BaseActivity() {
    private lateinit var binding: ActivityInvitedByBinding

    private lateinit var viewModel: InvitedByViewModel

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityInvitedByBinding

        viewModel = ViewModelProvider(this).get(InvitedByViewModel::class.java)

        var phoneNumberHere = intent.getStringExtra("phoneNumber")
        var invitedCheckDataClub = intent.getStringExtra("invitedCheckDataClub")
        var invitedCheckDataUser = intent.getStringExtra("invitedCheckDataUser")
        var userid = intent.getStringExtra("userid")

        if (userid != null) {
            viewModel.getMyFriendsHere(userid)
        }

        if (invitedCheckDataClub != "None") {
            binding.changingText.text =
                "you have been invited by ${invitedCheckDataUser} to join a clan"
            binding.diveInButton.setText("talk to ${invitedCheckDataUser}")
            binding.diveInButton.visibility = View.VISIBLE
            binding.diveInButton.setOnClickListener {
                prefHelper = PrefHelper(this)
                prefHelper.put(Constant.PREF_IS_LOGIN, true)
                val intent = Intent(this, LandingActivity::class.java).apply {
                    putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                }
                startActivity(intent)

//                val intent = Intent(this, StrangerIntroActivity::class.java).apply {
//                    putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
//                }
//                startActivity(intent)
//                overridePendingTransition(
//                    R.anim.slide_from_right ,
//                    R.anim.slide_out_left
//                )
            }
        } else {
            if (viewModel.friendsList.value?.size!! > 0) {
                binding.changingText.text =
                    "you have ${viewModel.friendsList.value?.size} friends on Aye!"
                binding.diveInButton.setText("talk to them")
                binding.diveInButton.visibility = View.VISIBLE
                binding.diveInButton.setOnClickListener {
                    prefHelper = PrefHelper(this)
                    prefHelper.put(Constant.PREF_IS_LOGIN, true)
                    val intent = Intent(this, LandingActivity::class.java).apply {
                        putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                    }
                    startActivity(intent)
                }
            } else {
                binding.changingText.text = "Aye! is fun only with friends"
                binding.diveInButton.setText("invite them")
                binding.diveInButton.visibility = View.VISIBLE
                binding.diveInButton.setOnClickListener {
                    val intent = Intent(this, StrangerIntroActivity::class.java).apply {
                        putExtra("phoneNumber", intent.getStringExtra("phoneNumber"))
                    }
                    startActivity(intent)
                    overridePendingTransition(
                        R.anim.slide_from_right,
                        R.anim.slide_out_left
                    )
                }
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityInvitedByBinding.inflate(layoutInflater)
    }

}