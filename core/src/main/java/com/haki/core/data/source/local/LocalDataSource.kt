package com.haki.core.data.source.local

import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.entity.FavoriteAstronomyEntity
import com.haki.core.data.source.local.room.AstronomyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val tourismDao: AstronomyDao) {

    fun getAllAstronomy(startDate: String, endDate: String): Flow<List<AstronomyEntity>> = tourismDao.getAstronomy(startDate = startDate, endDate = endDate)

    suspend fun insertAstronomy(astronomyList: List<AstronomyEntity>) = tourismDao.insertAstronomy(astronomyList)

    fun getFavoriteAstronomy(): Flow<List<FavoriteAstronomyEntity>> = tourismDao.getFavoriteAstronomy()

    suspend fun insertFavoriteAstronomy(astronomyList: List<FavoriteAstronomyEntity>) = tourismDao.insertFavoriteAstronomy(astronomyList)
}