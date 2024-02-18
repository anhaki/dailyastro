package com.haki.dailyastro.di

import com.haki.core.domain.usecase.AstronomyInteractor
import com.haki.core.domain.usecase.AstronomyUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideAstronomyUseCase(astronomyInteractor: AstronomyInteractor): AstronomyUseCase

}
