package com.example.hilt_sample.di

import com.example.hilt_sample.BuildConfig
import com.example.hilt_sample.core.interceptor.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providerRetrofit(client : OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.spacexdata.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    @Singleton
    @Provides
    fun providerAuthInterceptor() : AuthenticationInterceptor{
        return AuthenticationInterceptor()
    }

    @Singleton
    @Provides
    fun providerOkHttpClient(authenticationInterceptor: AuthenticationInterceptor) : OkHttpClient{
        val okHttpClient = OkHttpClient.Builder()

        //okHttpClient.addInterceptor(authenticationInterceptor)
        okHttpClient.addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })

        return okHttpClient.build()
    }



}