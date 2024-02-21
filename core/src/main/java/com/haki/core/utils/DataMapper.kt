package com.haki.core.utils

import com.haki.core.data.source.local.entity.AstronomyEntity
import com.haki.core.data.source.local.entity.FavoriteAstronomyEntity
import com.haki.core.data.source.remote.response.ApodResponse
import com.haki.core.domain.model.Astronomy

object DataMapper {
    fun mapResponsesToEntities(input: List<ApodResponse>): List<AstronomyEntity> {
        val astronomyList = ArrayList<AstronomyEntity>()
        input.map {
            val astronomy = AstronomyEntity(
                date = it.date,
                title = it.title,
                explanation = it.explanation,
                url = it.url,
                hdurl = it.hdurl
            )
            astronomyList.add(astronomy)
        }
        return astronomyList
    }

    fun mapEntitiesToDomain(input: List<AstronomyEntity>): List<Astronomy> =
        input.map {
            Astronomy(
                date = it.date,
                title = it.title,
                explanation = it.explanation,
                url = it.url,
                hdurl = it.hdurl ?: "",
            )
        }

    fun mapFavEntitiesToDomain(input: List<FavoriteAstronomyEntity>): List<Astronomy> =
        input.map {
            Astronomy(
                date = it.date,
                title = it.title,
                explanation = it.explanation,
                url = it.url,
                hdurl = it.hdurl ?: "",
            )
        }

    fun mapDomainToFavEntities(input: List<Astronomy>): List<FavoriteAstronomyEntity> {
        val astronomyList = ArrayList<FavoriteAstronomyEntity>()
        input.map {
            val astronomy = FavoriteAstronomyEntity(
                date = it.date,
                title = it.title,
                explanation = it.explanation,
                url = it.url,
                hdurl = it.hdurl
            )
            astronomyList.add(astronomy)
        }
        return astronomyList
    }
}