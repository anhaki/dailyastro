package com.haki.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ApodResponse(
    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("hdurl")
    val hdurl: String,

    @field:SerializedName("service_version")
    val serviceVersion: String,

    @field:SerializedName("explanation")
    val explanation: String,

    @field:SerializedName("title")
    val title: String,

    @field:SerializedName("url")
    val url: String
)

