package com.haki.dailyastro.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.usecase.AstronomyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(private val astronomyUseCase: AstronomyUseCase) : ViewModel() {
    fun getFavorite(): LiveData<List<Astronomy>> {
        return astronomyUseCase.getFavoriteAstronomy().asLiveData()
    }
}