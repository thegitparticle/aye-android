package com.example.toastgoand.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityHomeBinding
import com.example.toastgoand.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeActivity: BaseActivity() {

    private lateinit var binding: ActivityHomeBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityHomeBinding

    }

    override fun binding(): ViewBinding {
        return ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}