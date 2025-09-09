package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.api.CountryApiService
import com.example.walmartcodingassessment.data.model.Country
import com.example.walmartcodingassessment.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepositoryImpl(private val countryApiService: CountryApiService) : CountryRepository {
    override suspend fun getCountries(): Result<Country> {
        return withContext(Dispatchers.IO) {
            try {
                val response = countryApiService.getCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        Result.Success(it)
                    } ?: Result.Error(Exception("Response body is null"))
                } else {
                    Result.Error(Exception("API Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                Result.Error(e)
            }

        }
    }
}