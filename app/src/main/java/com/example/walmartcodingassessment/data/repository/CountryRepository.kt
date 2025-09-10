package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.model.CountryDTO
import com.example.walmartcodingassessment.data.DataResult


interface CountryRepository {
    suspend fun getCountries(): DataResult<CountryDTO>
}