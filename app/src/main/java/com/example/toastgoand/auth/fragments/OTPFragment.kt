package com.example.toastgoand.auth.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.auth.LoginActivity
import com.example.toastgoand.databinding.OtpFragmentBinding
import com.example.toastgoand.navigator.Navigator
import com.example.toastgoand.navigator.Screen
import javax.inject.Inject

class OTPFragment : Fragment() {

    private lateinit var viewModel: OTPViewModel
    lateinit var binding: OtpFragmentBinding
//    lateinit var splashLogo: ImageView

    @Inject
    lateinit var navigator: Navigator

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.otp_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(OTPViewModel::class.java)

        // Specify the fragment view as the lifecycle owner of the binding.
        // This is used so that the binding can observe LiveData updates
        binding.lifecycleOwner = viewLifecycleOwner

        val args = OTPFragmentArgs.fromBundle(requireArguments())

        binding.otpFragmentHeader.text = args.phoneNumber


//        splashLogo = binding.splashLogoImage

//        binding.enterButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") { view: View ->
//            navigator.navigateTo(Screen.HOME)
//        }

        return binding.root

    }

//    override fun onResume() {
//        super.onResume()
//        binding.enterButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER") { view: View ->
//            navigator.navigateTo(Screen.HOME)
//        }
//    }

}