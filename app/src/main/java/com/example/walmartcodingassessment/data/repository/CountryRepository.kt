package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.model.Country
import com.example.walmartcodingassessment.data.Result


interface CountryRepository {
    suspend fun getCountries(): Result<Country>
}