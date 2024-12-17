package com.htueko.tenki.core.data.datasource.remote.implementation

import com.google.common.truth.Truth
import com.htueko.tenki.core.data.datasource.remote.abstraction.RemoteWeatherDataSource
import com.htueko.tenki.core.data.service.RemoteWeatherService
import com.htueko.tenki.core.domain.model.dto.Current
import com.htueko.tenki.core.domain.model.dto.Location
import com.htueko.tenki.core.domain.model.dto.WeatherResponse
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import io.mockk.mockk
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before

import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import java.io.IOException

class RemoteWeatherDataSourceImplTest {

    private lateinit var server: MockWebServer
    private lateinit var mockRemoteWeatherService: RemoteWeatherService
    private lateinit var dataSource: RemoteWeatherDataSource
    private val apiKey = "test_api_key_1234"
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    private val jsonAdapter = moshi.adapter(WeatherResponse::class.java)
    private val client = OkHttpClient()
    private val testLocation = Location(name ="Tokyo")
    private val testCurrent = Current(
        tempC = 25.0,
        tempF = 77.0,
        feelslikeC = 26.0,
        feelslikeF = 78.0,
        uv = 1.1
    )
    private val errorMessage = "HTTP 400 Bad Request"
    private val runtimeException = RuntimeException("Network error")

    @Before
    fun setUp() {
        server = MockWebServer()
        server.start()
        mockRemoteWeatherService = mock<RemoteWeatherService>()
        dataSource = RemoteWeatherDataSourceImpl(mockRemoteWeatherService, apiKey)
    }

    @After
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `testSuccessfulWeatherRetrieval returns Success with WeatherResponse`() = runTest {
        val weatherResponse = WeatherResponse(testLocation, testCurrent)
        val json = jsonAdapter.toJson(weatherResponse)

        val retrofitResponse = retrofit2.Response.success(weatherResponse)

        whenever(mockRemoteWeatherService.getCurrentWeatherByLocation(apiKey, "Tokyo")).thenReturn(retrofitResponse)
        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        val result = dataSource.getCurrentWeatherByLocation("Tokyo")

        Truth.assertThat(result).isNotNull()
        val successResult = result as ResultOf.Success<WeatherResponse>
        Truth.assertThat(successResult.data.location.name).isEqualTo(testLocation.name)
        Truth.assertThat(successResult.data.current.tempC).isEqualTo(testCurrent.tempC)
        Truth.assertThat(successResult.data.current.tempF).isEqualTo(testCurrent.tempF)
        Truth.assertThat(successResult.data.current.feelslikeC).isEqualTo(testCurrent.feelslikeC)
        Truth.assertThat(successResult.data.current.feelslikeF).isEqualTo(testCurrent.feelslikeF)
        Truth.assertThat(successResult.data.current.uv).isEqualTo(testCurrent.uv)
    }

    @Test
    fun `testApiError returns ApiError`() = runTest {
        val errorBody = errorMessage.toResponseBody("text/plain".toMediaType())
        val retrofitResponse: retrofit2.Response<WeatherResponse> = retrofit2.Response.error(400, errorBody)

        whenever(mockRemoteWeatherService.getCurrentWeatherByLocation(apiKey, "London")).thenReturn(retrofitResponse)
        server.enqueue(MockResponse().setResponseCode(400).setBody(errorMessage))

        val result = dataSource.getCurrentWeatherByLocation("London")

        Truth.assertThat(result).isNotNull()
        assertTrue(result is ResultOf.ApiError)
        val apiErrorResult = result as ResultOf.ApiError<WeatherResponse>
        val actualErrorMessage = retrofitResponse.errorBody()?.string() ?: ""
        Truth.assertThat(actualErrorMessage).isNotEmpty()
        Truth.assertThat(actualErrorMessage).isEqualTo(errorMessage)
    }

    @Test
    fun `testNetworkError returns NetworkError`() = runTest {
        val weatherResponse = WeatherResponse(testLocation, testCurrent)
        val json = jsonAdapter.toJson(weatherResponse)

        val retrofitResponse = retrofit2.Response.success(weatherResponse)

        whenever(mockRemoteWeatherService.getCurrentWeatherByLocation(apiKey, "Tokyo")).thenReturn(retrofitResponse)
        whenever(mockRemoteWeatherService.getCurrentWeatherByLocation(apiKey, "New York")).thenThrow(runtimeException)
        server.enqueue(MockResponse().setResponseCode(200).setBody(json))

        val result = dataSource.getCurrentWeatherByLocation("New York")

        Truth.assertThat(result).isNotNull()
        assertTrue(result is ResultOf.NetworkError)
        val networkErrorResult = result as ResultOf.NetworkError<WeatherResponse>
        Truth.assertThat(networkErrorResult.throwable).isEqualTo(runtimeException)
        Truth.assertThat(networkErrorResult.throwable.message).isNotNull()
        Truth.assertThat(networkErrorResult.throwable.message).isNotEmpty()
        Truth.assertThat(networkErrorResult.throwable.message).isEqualTo(runtimeException.message)
    }

}