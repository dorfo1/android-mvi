package com.example.hilt_sample.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.hilt_sample.MainCoroutineRule
import com.example.hilt_sample.core.base.Resource
import com.example.hilt_sample.data.repository.RocketRepository
import com.example.hilt_sample.model.Rocket
import com.jraska.livedata.test
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
class MainViewModelTest {


    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: MainViewModel
    private val repository = mockk<RocketRepository>()


    @Before
    fun setUp() {
        viewModel = MainViewModel(repository)
    }


    @Test
    fun `State should be error when repository returns error`() {
        val state = viewModel.state.test()

        coEvery { repository.getRockets() } returns flow { emit( Resource.Error<List<Rocket>>(Exception())) }

        viewModel.setAction(MainAction.FetchRockets)

        verify(exactly = 1) {
            coroutineRule.launch {
                repository.getRockets()
            }
        }

        state.assertValue {
            it is MainState.Error
        }
    }

    @Test
    fun `State should be result rockets when repository returns success`() {
        val state = viewModel.state.test()

        coEvery { repository.getRockets() } returns flow { emit( Resource.Success<List<Rocket>>(listOf())) }

        viewModel.setAction(MainAction.FetchRockets)

        verify(exactly = 1) {
          coroutineRule.launch {
              repository.getRockets()
          }
        }

        state.assertValue {
            it is MainState.ResultRockets
        }
    }

    @Test
    fun `State should be result rockets when filter action after repository return success`(){
        val state = viewModel.state.test()

        coEvery { repository.getRockets() } returns flow { emit( Resource.Success<List<Rocket>>(listOf())) }

        viewModel.setAction(MainAction.FetchRockets)

        verify(exactly = 1) {
            runBlocking {
                repository.getRockets()
            }
        }

        viewModel.setAction(MainAction.FilterRockets(""))

        state.assertValue {
            it is MainState.ResultRockets
        }
    }

    @Test
    fun `State should be result rockets when clear action after repository return success`(){
        val state = viewModel.state.test()

        coEvery { repository.getRockets() } returns flow { emit( Resource.Success<List<Rocket>>(listOf())) }

        viewModel.setAction(MainAction.FetchRockets)

        verify(exactly = 1) {
            runBlocking {
                repository.getRockets()
            }
        }

        viewModel.setAction(MainAction.ClearFilter)


        state.assertValue {
            it is MainState.ResultRockets
        }
    }




}