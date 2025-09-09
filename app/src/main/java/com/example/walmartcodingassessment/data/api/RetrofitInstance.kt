package com.example.walmartcodingassessment.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
     private const val BASE_URL = "https://gist.githubusercontent.com"
     val countryApiService: CountryApiService by lazy {
          Retrofit.Builder()
               .baseUrl(BASE_URL)
               .addConverterFactory(GsonConverterFactory.create())
               .build()
               .create(CountryApiService::class.java)
     }
}