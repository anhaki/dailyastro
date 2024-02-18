package com.haki.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ListApodResponse(
    @field:SerializedName("ApodResponse")
    val apodResponse: List<ApodResponse>
)
