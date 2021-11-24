package com.example.toastgoand.home.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ActivityDummyXactivityBinding
import com.example.toastgoand.databinding.ActivityTheAyeBinding

class DummyXActivity : BaseActivity() {

    private lateinit var binding: ActivityDummyXactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBinding as ActivityDummyXactivityBinding
    }

    override fun binding(): ViewBinding {
        return ActivityDummyXactivityBinding.inflate(layoutInflater)
    }
}