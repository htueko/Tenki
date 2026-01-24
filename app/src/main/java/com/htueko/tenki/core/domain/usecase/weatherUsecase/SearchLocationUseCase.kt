package com.htueko.tenki.core.domain.usecase.weatherUsecase

import com.htueko.tenki.core.domain.model.CurrentWeather
import com.htueko.tenki.core.domain.model.SearchModel
import com.htueko.tenki.core.domain.model.status.ResultOf

/**
 * Performs a search for locations based on the provided query string.
 *
 * @param query The search query string.
 * @return A [ResultOf] containing a list of [SearchModel] instances representing the search results.
 */
interface SearchLocationUseCase {
    suspend operator fun invoke(
        query: String,
    ): ResultOf<List<SearchModel>>
}