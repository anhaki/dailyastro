package com.haki.core.domain.usecase

import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import kotlinx.coroutines.flow.Flow

interface AstronomyUseCase {
    fun getAllAstronomy(startDate: String, endDate: String): Flow<Resource<List<Astronomy>>>
//    fun getFavoriteTourism(): Flow<List<Astronomy>>
//    fun setFavoriteTourism(astronomy: Astronomy, state: Boolean)
}