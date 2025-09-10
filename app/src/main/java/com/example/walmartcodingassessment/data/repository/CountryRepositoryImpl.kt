package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.DataResult
import com.example.walmartcodingassessment.data.api.CountryApiService
import com.example.walmartcodingassessment.data.model.CountryDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CountryRepositoryImpl(private val countryApiService: CountryApiService) : CountryRepository {
    override suspend fun getCountries(): DataResult<CountryDTO> {
        return withContext(Dispatchers.IO) {
            try {
                val response = countryApiService.getCountries()
                if (response.isSuccessful) {
                    response.body()?.let {
                        DataResult.Success(it)
                    } ?: DataResult.Error(Exception("Response body is null"))
                } else {
                    DataResult.Error(Exception("API Error: ${response.code()} ${response.message()}"))
                }
            } catch (e: Exception) {
                DataResult.Error(e)
            }

        }
    }
}