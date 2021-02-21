package com.example.hilt_sample.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.hilt_sample.MainCoroutineRule
import com.example.hilt_sample.model.Rocket
import com.example.hilt_sample.ui.main.MainState
import com.jraska.livedata.test
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test

class DetailViewModelTest {


    @get:Rule
    val testRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun `State should be error when rocket is null`(){
        val state = viewModel.state.test()


        viewModel.setAction(DetailAction.DisplayRocket(null))

        state.assertValue {
            it is DetailState.Error
        }

    }

    @Test
    fun `State should be success when rocket is not null`(){
        val state = viewModel.state.test()

        val rocket = Rocket(
            name = "Name",
            photos = listOf("a","b"),
            kg = 1,
            description = "description"
        )

        viewModel.setAction(DetailAction.DisplayRocket(rocket))

        state.assertValue {
            it is DetailState.Success
        }
    }

}