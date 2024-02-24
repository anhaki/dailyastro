package com.haki.dynamicfeature.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.haki.core.domain.usecase.AstronomyUseCase

class FavoriteViewModel(astronomyUseCase: AstronomyUseCase) : ViewModel() {
    val favorite = astronomyUseCase.getFavoriteAstronomy().asLiveData()
}