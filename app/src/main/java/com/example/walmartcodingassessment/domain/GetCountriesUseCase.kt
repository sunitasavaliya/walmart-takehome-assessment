package com.example.walmartcodingassessment.domain

import com.example.walmartcodingassessment.data.Result
import com.example.walmartcodingassessment.data.model.Country
import com.example.walmartcodingassessment.data.repository.CountryRepository

class GetCountriesUseCase(private val countryRepository: CountryRepository) {
    suspend operator fun invoke(): Result<Country> {
        return when (val result = countryRepository.getCountries()) {
            is Result.Success<Country> -> {
                Result.Success(data = result.data.filter { !it.name.isNullOrEmpty() }) as Result<Country>
            }

            else -> result
        }
    }
}