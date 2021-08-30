package com.example.toastgoand.home.directs

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.toastgoand.R
import com.example.toastgoand.databinding.DirectsFragmentBinding
import com.example.toastgoand.home.clans.ClansViewModel

class DirectsFragment : Fragment() {

    private lateinit var viewModel: DirectsViewModel
    private lateinit var binding: DirectsFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.directs_fragment,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(DirectsViewModel::class.java)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(DirectsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}