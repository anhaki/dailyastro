package com.haki.core.utils

import com.haki.core.data.source.local.entity.AstronomyEntity
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

    fun mapDomainToEntity(input: Astronomy) = AstronomyEntity(
        date = input.date,
        title = input.title,
        explanation = input.explanation,
        url = input.url,
        hdurl = input.hdurl
    )
}