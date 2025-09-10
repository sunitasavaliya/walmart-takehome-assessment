package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.Result
import com.example.walmartcodingassessment.data.api.CountryApiService
import com.example.walmartcodingassessment.data.model.Country
import com.example.walmartcodingassessment.data.model.CountryItem
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import retrofit2.Response


@ExperimentalCoroutinesApi
class CountryRepositoryImplTest {

    private val testDispatcher = StandardTestDispatcher()

    @Mock
    private lateinit var mockApiService: CountryApiService

    private lateinit var repository: CountryRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        repository = CountryRepositoryImpl(mockApiService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getCountries success returns Success result with data`() = runTest(testDispatcher) {
        // Arrange
        val countryList = Country()
        countryList.add(
            CountryItem(
                capital = "Kabul",
                code = "AF",
                name = "Afghanistan",
                region = "AS",
                currency = null,
                demonym = null,
                flag = null,
                language = null
            )
        )
        countryList.add(
            CountryItem(
                capital = "Mariehamn",
                code = "AX",
                name = "Åland Islands",
                region = "EU",
                currency = null,
                demonym = null,
                flag = null,
                language = null
            )
        )

        `when`(mockApiService.getCountries()).thenReturn(Response.success(countryList))

        // Act
        val result = repository.getCountries()

        // Assert
        val successResult = result as Result.Success
        assertThat(successResult.data).isEqualTo(countryList)
        assertThat(successResult.data).hasSize(2)
        assertThat(successResult.data[0].name).isEqualTo("Afghanistan")
    }

    @Test
    fun `getCountries API success but body is null returns Error result`() =
        runTest(testDispatcher) {
            // Arrange
            val nullBodyResponse: Response<Country> =
                Response.success(null)
            `when`(mockApiService.getCountries()).thenReturn(nullBodyResponse)

            // Act
            val result = repository.getCountries()

            // Assert
            assertThat(result).isInstanceOf(Result.Error::class.java)
            val errorResult = result as Result.Error
            assertThat(errorResult.exception.message).isEqualTo("Response body is null")
        }
}