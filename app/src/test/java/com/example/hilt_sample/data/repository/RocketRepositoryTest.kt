package com.example.hilt_sample.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.example.hilt_sample.MainCoroutineRule
import com.example.hilt_sample.core.base.Resource
import com.example.hilt_sample.data.service.RocketService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.lang.Exception
import kotlin.time.ExperimentalTime


@ExperimentalCoroutinesApi
class RocketRepositoryTest {


    @get:Rule
    val testRule = InstantTaskExecutorRule()


    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var repositoryImpl: RocketRepositoryImpl
    private val service = mockk<RocketService>()


    @Before
    fun setUp(){
        repositoryImpl = RocketRepositoryImpl(service)
    }

    @ExperimentalTime
    @Test
    fun `should emit success when server return success`(){
        coEvery { service.getRockets() } .returns( listOf() )

        coroutineRule.launch {
            repositoryImpl.getRockets().test {
                assert(expectItem() is Resource.Loading)
                assert(expectItem() is Resource.Success)
                expectComplete()
            }
        }

    }

    @ExperimentalTime
    @Test
    fun `should emit error when server return error`(){
        coEvery { service.getRockets() } throws  Exception()

        coroutineRule.launch {
            repositoryImpl.getRockets().test {
                assert(expectItem() is Resource.Loading)
                assert(expectItem() is Resource.Error)
                expectComplete()
            }
        }

    }
}