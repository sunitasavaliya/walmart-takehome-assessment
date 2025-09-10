package com.example.walmartcodingassessment.domain.model

import com.example.walmartcodingassessment.data.model.CountryItem

data class Country(
    val name: String,
    val region: String,
    val capital: String,
    val code: String,
)

fun CountryItem.toCountry(): Country {
    return Country(
        name = name,
        region = region,
        capital = capital,
        code = code
    )
}