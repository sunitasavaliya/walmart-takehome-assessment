package com.example.walmartcodingassessment.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.walmartcodingassessment.data.api.RetrofitInstance
import com.example.walmartcodingassessment.data.repository.CountryRepositoryImpl

class CountryViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CountryViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CountryViewModel(CountryRepositoryImpl(RetrofitInstance.countryApiService)) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}