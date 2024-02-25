package com.haki.core.data

import com.haki.core.data.source.local.LocalDataSource
import com.haki.core.data.source.remote.RemoteDataSource
import com.haki.core.data.source.remote.network.ApiResponse
import com.haki.core.data.source.remote.response.ApodResponse
import com.haki.core.domain.model.Astronomy
import com.haki.core.domain.repository.IAstronomyRepository
import com.haki.core.utils.AppExecutors
import com.haki.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AstronomyRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : IAstronomyRepository {

    override fun getAllAstronomy(
        startDate: String,
        endDate: String
    ): Flow<Resource<List<Astronomy>>> =
        object : NetworkBoundResource<List<Astronomy>, List<ApodResponse>>() {
            override fun loadFromDB(): Flow<List<Astronomy>> {
                return localDataSource.getAllAstronomy(startDate, endDate).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Astronomy>?): Boolean {
                return data.isNullOrEmpty() || ((data.first().date != startDate) || (data.last().date != endDate))
            }

            override suspend fun createCall(): Flow<ApiResponse<List<ApodResponse>>> {
                return remoteDataSource.getAllAstronomy(startDate, endDate)
            }

            override suspend fun saveCallResult(data: List<ApodResponse>) {
                val astronomyList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAstronomy(astronomyList)
            }
        }.asFlow()

    override fun getFavoriteAstronomy(): Flow<List<Astronomy>> {
        return localDataSource.getFavoriteAstronomy().map {
            DataMapper.mapFavEntitiesToDomain(it)
        }
    }

    override fun isFavorite(date: String): Flow<List<Astronomy>> {
        return localDataSource.isFavorite(date).map {
            DataMapper.mapFavEntitiesToDomain(it)
        }
    }

    override fun deleteFavorite(date: String) {
        appExecutors.diskIO().execute { localDataSource.deleteFavorite(date) }
    }

    override fun setFavoriteAstronomy(data: List<Astronomy>) {
        val astronomyList = DataMapper.mapDomainToFavEntities(data)
        appExecutors.diskIO().execute { localDataSource.insertFavoriteAstronomy(astronomyList) }

    }
}

