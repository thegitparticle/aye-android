package com.example.toastgoand.auth

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.SimpleAdapter
import android.widget.Toast
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.auth.fragments.EnterPhoneViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import com.example.toastgoand.auth.fragments.EnterPhoneFragment
import com.example.toastgoand.databinding.EnterPhoneBinding
import com.example.toastgoand.databinding.LoginBinding
import com.example.toastgoand.navigator.Navigator
import java.util.Date.from
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity: BaseActivity() {

    private lateinit var binding: LoginBinding

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as LoginBinding

    }

    override fun binding(): ViewBinding {
        return LoginBinding.inflate(layoutInflater)
    }

    override fun onBackPressed() {
        finishAffinity()
    }

}



