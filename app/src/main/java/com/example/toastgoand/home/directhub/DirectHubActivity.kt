package com.example.toastgoand.home.directhub

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityClanHubBinding
import com.example.toastgoand.databinding.ActivityDirectHubBinding
import com.example.toastgoand.dummy.DummyDirectHub
import com.example.toastgoand.home.clanhub.ClanHubViewModel
import com.example.toastgoand.home.directhub.components.DirectHubUserDetails
import com.example.toastgoand.home.directhub.components.DirectHubUserMetrics
import com.google.accompanist.appcompattheme.AppCompatTheme
import com.google.android.material.composethemeadapter.MdcTheme

class DirectHubActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectHubBinding

    private lateinit var viewModel: DirectHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectHubBinding

        setContent {
            AppCompatTheme {
                DirectHubUserDetails(userProfile = DummyDirectHub.directHub[0])
                DirectHubUserMetrics(userProfile = DummyDirectHub.directHub[0])
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityDirectHubBinding.inflate(layoutInflater)
    }

}