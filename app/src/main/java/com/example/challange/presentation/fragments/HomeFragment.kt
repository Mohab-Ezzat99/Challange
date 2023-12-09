package com.example.challange.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.example.challange.R
import com.example.challange.data.remote.dto.Rates
import com.example.challange.data.remote.dto.Symbols
import com.example.challange.databinding.FragmentHomeBinding
import com.example.challange.presentation.BaseFragment
import com.example.challange.presentation.MainViewModel
import com.example.challange.util.SpinnerUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentHomeBinding::inflate

    private lateinit var rates: Rates
    private val viewModel: MainViewModel by viewModels()

    override fun setUI(view: View, savedInstanceState: Bundle?) {
        setupRightSpinner()
        setupLeftSpinner()
    }

    override fun clicks() {

    }

    override fun callApis() {
        viewModel.getSymbols()
        viewModel.latest()
    }

    override fun observer() {
        lifecycleScope.launchWhenCreated {
            viewModel.ratesResponse.collectLatest { data ->
                data?.let {
                    rates = it
                    binding.tvLeftValue.setText(calcValue(1, 1).toString())
                }
            }
        }
    }

    private fun setupRightSpinner() {
        val list = listOf(
            "EUR",
            "AED",
            "AUD",
            "EGP",
            "USD"
        )
        SpinnerUtil(
            context = requireContext(),
            binding = binding.inSpinnerRight,
            list = list.toMutableList(),
            labels = list.toMutableList(),
            selectedItem = "EUR"
        )
    }

    private fun setupLeftSpinner() {
        val list = listOf(
            "EUR",
            "AED",
            "AUD",
            "EGP",
            "USD"
        )
        SpinnerUtil(
            context = requireContext(),
            binding = binding.inSpinnerLeft,
            list = list.toMutableList(),
            labels = list.toMutableList(),
            selectedItem = "EGP"
        )
    }

    private fun calcValue(position: Int, base: Int): Double {
        return when (position) {
            0 -> base * rates.EUR!!
            1 -> base * rates.AED!!
            2 -> base * rates.AUD!!
            3 -> base * rates.EGP!!
            4 -> base * rates.USD!!
            else -> 0.0
        }
    }

}