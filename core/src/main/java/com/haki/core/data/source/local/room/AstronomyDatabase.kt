package com.haki.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.entity.FavoriteAstronomyEntity

@Database(
    entities = [AstronomyEntity::class, FavoriteAstronomyEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AstronomyDatabase : RoomDatabase() {

    abstract fun astronomyDao(): AstronomyDao
}