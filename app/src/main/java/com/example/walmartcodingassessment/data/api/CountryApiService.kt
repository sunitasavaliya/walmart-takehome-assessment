package com.example.walmartcodingassessment.data.api

import com.example.walmartcodingassessment.data.model.Country
import retrofit2.Response
import retrofit2.http.GET

interface CountryApiService {
    @GET("/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json")
    suspend fun getCountries(): Response<Country>

}