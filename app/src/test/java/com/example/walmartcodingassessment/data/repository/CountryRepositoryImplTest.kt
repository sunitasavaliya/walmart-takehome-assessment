package com.example.walmartcodingassessment.data.repository

import com.example.walmartcodingassessment.data.DataResult
import com.example.walmartcodingassessment.data.api.CountryApiService
import com.example.walmartcodingassessment.data.model.CountryDTO
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
        val countryDTOList = CountryDTO()
        countryDTOList.add(
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
        countryDTOList.add(
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

        `when`(mockApiService.getCountries()).thenReturn(Response.success(countryDTOList))

        // Act
        val result = repository.getCountries()

        // Assert
        val successDataResult = result as DataResult.Success
        assertThat(successDataResult.data).isEqualTo(countryDTOList)
        assertThat(successDataResult.data).hasSize(2)
        assertThat(successDataResult.data[0].name).isEqualTo("Afghanistan")
    }

    @Test
    fun `getCountries API success but body is null returns Error result`() =
        runTest(testDispatcher) {
            // Arrange
            val nullBodyResponse: Response<CountryDTO> =
                Response.success(null)
            `when`(mockApiService.getCountries()).thenReturn(nullBodyResponse)

            // Act
            val result = repository.getCountries()

            // Assert
            assertThat(result).isInstanceOf(DataResult.Error::class.java)
            val errorDataResult = result as DataResult.Error
            assertThat(errorDataResult.exception.message).isEqualTo("Response body is null")
        }
}