package com.example.toastgoand.home.directhub

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.composestyle.AyeTheme
import com.example.toastgoand.databinding.ActivityDirectHubBinding
import com.example.toastgoand.home.directhub.components.DirectHubUserDetails
import com.example.toastgoand.home.directhub.components.DirectHubUserMetrics

class DirectHubActivity : BaseActivity() {
    private lateinit var binding: ActivityDirectHubBinding

    private lateinit var viewModel: DirectHubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDirectHubBinding

        setContent {
            AyeTheme() {
            }
        }
    }

    override fun binding(): ViewBinding {
        return ActivityDirectHubBinding.inflate(layoutInflater)
    }

}