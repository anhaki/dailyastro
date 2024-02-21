package com.haki.core.domain.usecase

import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.repository.IAstronomyRepository
import javax.inject.Inject

class AstronomyInteractor @Inject constructor(private val astronomyRepository: IAstronomyRepository): AstronomyUseCase {

    override fun getAllAstronomy(startDate: String, endDate: String) = astronomyRepository.getAllAstronomy(startDate, endDate)

    override fun getFavoriteAstronomy() = astronomyRepository.getFavoriteAstronomy()

    override suspend fun setFavoriteAstronomy(data: List<Astronomy>) = astronomyRepository.setFavoriteAstronomy(data)
}