package com.example.walmartcodingassessment.data.model

data class CountryItem(
    val capital: String,
    val code: String,
    val currency: Currency?,
    val demonym: String?,
    val flag: String?,
    val language: Language?,
    val name: String,
    val region: String
)