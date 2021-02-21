package com.example.hilt_sample.data.repository

import com.example.hilt_sample.core.base.Resource
import com.example.hilt_sample.model.Rocket
import kotlinx.coroutines.flow.Flow

interface RocketRepository{
    suspend fun getRockets() : Flow<Resource<List<Rocket>>>
}