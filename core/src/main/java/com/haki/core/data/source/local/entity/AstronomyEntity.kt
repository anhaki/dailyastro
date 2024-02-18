package com.haki.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "astronomy")
data class AstronomyEntity(
    @PrimaryKey
    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "explanation")
    var explanation: String,

    @ColumnInfo(name = "url")
    var url: String,

    @ColumnInfo(name = "hdurl")
    var hdurl: String?
)
