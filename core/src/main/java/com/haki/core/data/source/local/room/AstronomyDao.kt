package com.haki.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.entity.FavoriteAstronomyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AstronomyDao {

    @Query("SELECT * FROM astronomy WHERE DATE(date) BETWEEN :startDate AND :endDate")
    fun getAstronomy(startDate: String, endDate: String): Flow<List<AstronomyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronomy(astronomy: List<AstronomyEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteAstronomy(astronomy: List<FavoriteAstronomyEntity>)

    @Query("SELECT * FROM favoriteAstronomy ORDER BY date ASC")
    fun getFavoriteAstronomy(): Flow<List<FavoriteAstronomyEntity>>

    @Query("DELETE FROM favoriteAstronomy WHERE date = :date")
    fun deleteFavorite(date: String)

    @Query("SELECT * FROM favoriteAstronomy WHERE date = :date")
    fun isFavorite(date: String): Flow<List<FavoriteAstronomyEntity>>
}
