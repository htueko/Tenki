package com.htueko.tenki.core.domain.usecase.weatherUsecase

import com.htueko.tenki.core.domain.model.SearchModel
import com.htueko.tenki.core.domain.model.status.ResultOf
import com.htueko.tenki.core.domain.repository.RemoteWeatherRepository
import javax.inject.Inject

/**
 * Implements the [SearchLocationUseCase] interface to search for locations using the [RemoteWeatherRepository].
 *
 * @param remoteWeatherRepository the repository used to search for locations
 */
class SearchLocationUseCaseImpl @Inject constructor(
    private val remoteWeatherRepository: RemoteWeatherRepository,
) : SearchLocationUseCase {
    override suspend fun invoke(query: String): ResultOf<List<SearchModel>> {
        return remoteWeatherRepository.searchLocation(query)
    }

}