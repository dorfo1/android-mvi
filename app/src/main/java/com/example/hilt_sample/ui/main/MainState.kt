package com.example.hilt_sample.ui.main

import com.example.hilt_sample.core.base.ViewState
import com.example.hilt_sample.model.Rocket
import java.lang.Exception

sealed class MainState : ViewState {
    object LoadingState : MainState()
    data class ResultRockets(val rockets : List<Rocket>) : MainState()
    data class RocketDetail(val rocket: Rocket) : MainState()
    data class Error(val exception: Exception) : MainState()
}