package com.example.hilt_sample.data.repository

import com.example.hilt_sample.core.base.Resource
import com.example.hilt_sample.data.service.RocketService
import com.example.hilt_sample.model.Rocket
import kotlinx.coroutines.flow.*
import retrofit2.HttpException
import java.lang.Exception

class RocketRepositoryImpl constructor(
    private val service: RocketService
) : RocketRepository {

    override suspend fun getRockets(): Flow<Resource<List<Rocket>>> = flow {
        emit(Resource.Loading())
        val response = service.getRockets()
        emit(Resource.Success(response.map { Rocket.fromResponse(it) }))
    }.catch { ex ->
        emit(Resource.Error(exception = Exception(ex)))
    }
}

