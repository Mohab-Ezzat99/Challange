package com.example.challange.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challange.data.remote.dto.Rates
import com.example.challange.data.remote.dto.Symbols
import com.example.challange.data.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val _symbolsResponse = MutableStateFlow<Symbols?>(null)
    val symbolsResponse: StateFlow<Symbols?> = _symbolsResponse

    private val _ratesResponse = MutableStateFlow<Rates?>(null)
    val ratesResponse: StateFlow<Rates?> = _ratesResponse

    fun getSymbols() {
        viewModelScope.launch {
            mainRepository.getSymbols().collect {
                _symbolsResponse.value = it.data?.symbols
            }
        }
    }

    fun latest() {
        viewModelScope.launch {
            mainRepository.latest().collect {
                _ratesResponse.value = it.data?.rates
            }
        }
    }

}