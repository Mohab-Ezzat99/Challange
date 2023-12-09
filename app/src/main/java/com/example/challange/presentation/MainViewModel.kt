package com.example.challange.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.challange.data.remote.dto.ConvertResponse
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

    private val _convertResponse = MutableStateFlow<Double?>(null)
    val convertResponse: StateFlow<Double?> = _convertResponse

    fun latest() {
        viewModelScope.launch {
            mainRepository.latest().collect {
                _convertResponse.value = it.data?.rates?.AED
            }
        }
    }

}