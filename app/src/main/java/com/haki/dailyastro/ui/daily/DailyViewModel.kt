package com.haki.dailyastro.ui.daily

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.haki.core.data.Resource
import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.usecase.AstronomyUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DailyViewModel @Inject constructor(private val astronomyUseCase: AstronomyUseCase) :
    ViewModel() {

    fun getAstro(startDate: String, endDate: String): LiveData<Resource<List<Astronomy>>> {
        return astronomyUseCase.getAllAstronomy(startDate, endDate).asLiveData()
    }
}
