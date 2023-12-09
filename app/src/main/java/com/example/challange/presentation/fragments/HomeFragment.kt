package com.example.challange.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.challange.R
import com.example.challange.databinding.FragmentHomeBinding
import com.example.challange.presentation.BaseFragment
import com.example.challange.presentation.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private val viewModel: MainViewModel by viewModels()

    override fun setUI(view: View, savedInstanceState: Bundle?) {
        viewModel.latest()
    }

    override fun clicks() {

    }

    override fun callApis() {

    }

    override fun observer() {
        lifecycleScope.launchWhenCreated {
            viewModel.convertResponse.collectLatest{
                Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

}