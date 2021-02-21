package com.example.hilt_sample.data.service

import com.example.hilt_sample.data.dto.RocketResponse
import retrofit2.http.GET

interface RocketService {

    @GET("v4/rockets")
    suspend fun getRockets(): List<RocketResponse>

}