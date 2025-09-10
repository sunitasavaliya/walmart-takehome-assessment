package com.example.walmartcodingassessment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.walmartcodingassessment.data.Result
import com.example.walmartcodingassessment.domain.GetCountriesUseCase
import com.example.walmartcodingassessment.ui.view.uistate.UIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) : ViewModel() {
    private val _uiStates = MutableStateFlow<UIState>(UIState.Loading)
    val uiState: StateFlow<UIState> = _uiStates.asStateFlow()

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            when (val result = getCountriesUseCase()) {
                is Result.Success -> {
                    _uiStates.value = UIState.Success(result.data)
                }

                is Result.Error -> {
                    _uiStates.value = UIState.Error("Error fetching countries")
                }
            }
        }
    }
}