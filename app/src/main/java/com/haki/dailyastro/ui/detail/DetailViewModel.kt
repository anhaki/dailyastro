package com.haki.dailyastro.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.usecase.AstronomyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val astronomyUseCase: AstronomyUseCase) :
    ViewModel() {
    fun isFav(date: String): LiveData<Boolean> {
        return astronomyUseCase.isFavorite(date).map { it.isNotEmpty() }.asLiveData()
    }

    fun setAstronomyFavorite(data: List<Astronomy>) {
        astronomyUseCase.setFavoriteAstronomy(data)
    }

    fun deleteFavorite(date: String) {
        astronomyUseCase.deleteFavorite(date)
    }
}
