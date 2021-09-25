package com.example.toastgoand.auth.clancreate

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanCreatedBinding
import com.example.toastgoand.home.LandingActivity
import com.example.toastgoand.prefhelpers.Constant
import com.example.toastgoand.prefhelpers.PrefHelper

class ClanCreatedActivity : BaseActivity() {
    private lateinit var binding: ActivityClanCreatedBinding

    private lateinit var viewModel: ClanCreatedViewModel

    lateinit var prefHelper: PrefHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityClanCreatedBinding

        viewModel = ViewModelProvider(this).get(ClanCreatedViewModel::class.java)

        binding.allowContactsButton.setOnClickListener {
            prefHelper = PrefHelper(this)
            prefHelper.put( Constant.PREF_IS_LOGIN, true)
            val intent = Intent(this, LandingActivity::class.java).apply {
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityClanCreatedBinding.inflate(layoutInflater)
    }
}