package com.haki.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.haki.core.data.source.local.entity.AstronomyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AstronomyDao {

    @Query("SELECT * FROM astronomy WHERE DATE(date) BETWEEN :startDate AND :endDate")
    fun getAstronomy(startDate: String, endDate: String): Flow<List<AstronomyEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAstronomy(tourism: List<AstronomyEntity>)

    @Query("SELECT * FROM astronomy where isFavorite = 1")
    fun getFavoriteAstronomy(): Flow<List<AstronomyEntity>>

    @Update
    fun updateFavoriteAstronomy(tourism: AstronomyEntity)

}
