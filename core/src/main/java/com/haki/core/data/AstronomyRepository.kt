package com.haki.core.data

import android.util.Log
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

    override fun getAllAstronomy(startDate: String, endDate: String): Flow<Resource<List<Astronomy>>> =
        object : NetworkBoundResource<List<Astronomy>, List<ApodResponse>>() {
            override fun loadFromDB(): Flow<List<Astronomy>> {
                return localDataSource.getAllAstronomy(startDate, endDate).map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Astronomy>?): Boolean =
                data.isNullOrEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<ApodResponse>>> {
                Log.d("ancika", "soialn")
                return remoteDataSource.getAllAstronomy(startDate, endDate)

            }

            override suspend fun saveCallResult(data: List<ApodResponse>) {
                val tourismList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertAstronomy(tourismList)
            }
        }.asFlow()
}

