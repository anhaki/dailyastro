package com.haki.dailyastro.ui.apod

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ApodViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is apod Fragment"
    }
    val text: LiveData<String> = _text
}