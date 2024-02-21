package com.haki.core.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Astronomy(
    val date: String,
    val hdurl: String,
    val explanation: String,
    val title: String,
    val url: String,
    val isFavorite: Boolean = false
) : Parcelable