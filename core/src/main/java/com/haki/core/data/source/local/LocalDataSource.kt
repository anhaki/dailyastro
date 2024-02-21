package com.haki.core.data.source.local

import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.room.AstronomyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val tourismDao: AstronomyDao) {

    fun getAllAstronomy(startDate: String, endDate: String): Flow<List<AstronomyEntity>> = tourismDao.getAstronomy(startDate = startDate, endDate = endDate)

    fun getFavoriteAstronomy(): Flow<List<AstronomyEntity>> = tourismDao.getFavoriteAstronomy()

    suspend fun insertAstronomy(tourismList: List<AstronomyEntity>) = tourismDao.insertAstronomy(tourismList)

    fun setFavoriteAstronomy(astronomy: AstronomyEntity, newState: Boolean) {
        astronomy.isFavorite = newState
        tourismDao.updateFavoriteAstronomy(astronomy)
    }
}