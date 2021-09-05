package com.example.toastgoand.home.clans

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.ClansFragmentBinding

class ClansFragment : Fragment() {

    private lateinit var binding: ClansFragmentBinding
    private lateinit var viewModel: ClansViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.clans_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(ClansViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ClansViewModel::class.java)
        // TODO: Use the ViewModel
    }

}