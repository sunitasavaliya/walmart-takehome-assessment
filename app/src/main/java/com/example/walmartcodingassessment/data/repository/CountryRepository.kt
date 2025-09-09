package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.model.Country
import com.example.walmartcodingassessment.util.Result


interface CountryRepository {
    suspend fun getCountries(): Result<Country>
}