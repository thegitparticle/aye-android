package com.example.toastgoand.quick

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityStreamCameraBinding
import com.example.toastgoand.databinding.ActivityStreamMultiBinding

class StreamMultiActivity : BaseActivity() {

    private lateinit var binding: ActivityStreamMultiBinding
    private lateinit var viewModel: StreamMultiViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityStreamMultiBinding

        viewModel = ViewModelProvider(this).get(StreamMultiViewModel::class.java)
    }

    override fun binding(): ViewBinding {
        return ActivityStreamMultiBinding.inflate(layoutInflater)
    }
}