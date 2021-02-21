package com.example.hilt_sample.ui.detail

import com.example.hilt_sample.core.base.ViewState
import com.example.hilt_sample.model.Rocket

sealed class DetailState : ViewState {

    data class Success(val rocket: Rocket) : DetailState()
    object Error : DetailState()
}