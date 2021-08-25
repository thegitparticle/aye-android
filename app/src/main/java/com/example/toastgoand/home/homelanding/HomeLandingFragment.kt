package com.example.toastgoand.home.homelanding

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.toastgoand.R

class HomeLandingFragment : Fragment() {

    companion object {
        fun newInstance() = HomeLandingFragment()
    }

    private lateinit var viewModel: HomeLandingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_landing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeLandingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}