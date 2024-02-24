package com.haki.core.data.source.local

import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.entity.FavoriteAstronomyEntity
import com.haki.core.data.source.local.room.AstronomyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val astronomyDao: AstronomyDao) {

    fun getAllAstronomy(startDate: String, endDate: String): Flow<List<AstronomyEntity>> =
        astronomyDao.getAstronomy(startDate = startDate, endDate = endDate)

    suspend fun insertAstronomy(astronomyList: List<AstronomyEntity>) =
        astronomyDao.insertAstronomy(astronomyList)

    fun getFavoriteAstronomy(): Flow<List<FavoriteAstronomyEntity>> =
        astronomyDao.getFavoriteAstronomy()

    fun isFavorite(date: String): Flow<List<FavoriteAstronomyEntity>> =
        astronomyDao.isFavorite(date)

    fun deleteFavorite(date: String) {
        astronomyDao.deleteFavorite(date)
    }

    fun insertFavoriteAstronomy(astronomyList: List<FavoriteAstronomyEntity>) =
        astronomyDao.insertFavoriteAstronomy(astronomyList)
}