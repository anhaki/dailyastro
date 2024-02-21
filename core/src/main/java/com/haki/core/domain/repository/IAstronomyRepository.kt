package com.haki.core.domain.repository

import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import kotlinx.coroutines.flow.Flow

interface IAstronomyRepository {

    fun getAllAstronomy(startDate: String, endDate: String): Flow<Resource<List<Astronomy>>>

    fun getFavoriteAstronomy(): Flow<List<Astronomy>>

    suspend fun setFavoriteAstronomy(data: List<Astronomy>)

}