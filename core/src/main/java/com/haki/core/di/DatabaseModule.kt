package com.haki.core.di

import android.content.Context
import androidx.room.Room
import com.haki.core.data.source.local.room.AstronomyDao
import com.haki.core.data.source.local.room.AstronomyDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): AstronomyDatabase =
        Room.databaseBuilder(
            context,
            AstronomyDatabase::class.java, "Astronomy.db"
        ).fallbackToDestructiveMigration().build()

    @Provides
    fun provideAstronomyDao(database: AstronomyDatabase): AstronomyDao = database.astronomyDao()
}