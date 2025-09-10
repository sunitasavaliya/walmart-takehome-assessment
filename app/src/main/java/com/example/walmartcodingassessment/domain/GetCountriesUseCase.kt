package com.example.walmartcodingassessment.domain

import com.example.walmartcodingassessment.data.DataResult
import com.example.walmartcodingassessment.data.model.CountryDTO
import com.example.walmartcodingassessment.data.repository.CountryRepository
import com.example.walmartcodingassessment.domain.model.Country
import com.example.walmartcodingassessment.domain.model.toCountry

class GetCountriesUseCase(private val countryRepository: CountryRepository) {
    suspend operator fun invoke(): DomainResult<List<Country>> {
        return when (val result = countryRepository.getCountries()) {
            is DataResult.Success<CountryDTO> -> {
                return DomainResult.Success(result.data.filter { it.name.isNotEmpty() }
                    .map { countries -> countries.toCountry() })
            }

            else -> DomainResult.Error((result as DataResult.Error).exception)
        }
    }
}