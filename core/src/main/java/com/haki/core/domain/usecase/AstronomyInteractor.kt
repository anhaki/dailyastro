package com.haki.core.domain.usecase

import com.haki.core.domain.repository.IAstronomyRepository
import javax.inject.Inject

class AstronomyInteractor @Inject constructor(private val tourismRepository: IAstronomyRepository): AstronomyUseCase {

    override fun getAllAstronomy(startDate: String, endDate: String) = tourismRepository.getAllAstronomy(startDate, endDate)


//    override fun getFavoriteTourism() = tourismRepository.getFavoriteTourism()

//    override fun setFavoriteTourism(astronomy: Astronomy, state: Boolean) = tourismRepository.setFavoriteTourism(astronomy, state)
}