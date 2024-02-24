package com.haki.core.domain.usecase

import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.repository.IAstronomyRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AstronomyInteractor @Inject constructor(private val astronomyRepository: IAstronomyRepository) :
    AstronomyUseCase {

    override fun getAllAstronomy(startDate: String, endDate: String) =
        astronomyRepository.getAllAstronomy(startDate, endDate)

    override fun getFavoriteAstronomy() = astronomyRepository.getFavoriteAstronomy()

    override fun isFavorite(date: String): Flow<List<Astronomy>> =
        astronomyRepository.isFavorite(date)

    override fun deleteFavorite(date: String) {
        astronomyRepository.deleteFavorite(date)
    }

    override fun setFavoriteAstronomy(data: List<Astronomy>) =
        astronomyRepository.setFavoriteAstronomy(data)
}