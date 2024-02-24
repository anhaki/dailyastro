package com.haki.dailyastro.di

import com.haki.core.domain.usecase.AstronomyUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavModuleDependencies {

    fun astronomyUseCase(): AstronomyUseCase
}