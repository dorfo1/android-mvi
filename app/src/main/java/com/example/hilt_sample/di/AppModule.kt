package com.example.hilt_sample.di

import com.example.hilt_sample.data.repository.RocketRepository
import com.example.hilt_sample.data.repository.RocketRepositoryImpl
import com.example.hilt_sample.data.service.RocketService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRocketService(retrofit: Retrofit): RocketService {
        return retrofit.create(RocketService::class.java)
    }

    @Singleton
    @Provides
    fun provideRocketRepository(service: RocketService) : RocketRepository {
        return RocketRepositoryImpl(service)
    }

}