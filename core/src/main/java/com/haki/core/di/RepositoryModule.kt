package com.dicoding.tourismapp.core.di

import com.haki.core.data.AstronomyRepository
import com.haki.core.di.DatabaseModule
import com.haki.core.di.NetworkModule
import com.haki.core.domain.repository.IAstronomyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(tourismRepository: AstronomyRepository): IAstronomyRepository

}