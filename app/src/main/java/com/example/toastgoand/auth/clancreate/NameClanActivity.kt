package com.example.toastgoand.auth.clancreate

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.toastgoand.BaseActivity
import com.example.toastgoand.databinding.ActivityNameClanBinding

class NameClanActivity : BaseActivity() {
    private lateinit var binding: ActivityNameClanBinding

    private lateinit var viewModel: NameClanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = viewBinding as ActivityNameClanBinding

        viewModel = ViewModelProvider(this).get(NameClanViewModel::class.java)
        binding.nameClanModel = viewModel

        binding.nextImageButton.setOnClickListener {
            val intent = Intent(this, ClanCreatedActivity::class.java).apply{
            }
            startActivity(intent)
        }

    }

    override fun binding(): ViewBinding {
        return ActivityNameClanBinding.inflate(layoutInflater)
    }
}