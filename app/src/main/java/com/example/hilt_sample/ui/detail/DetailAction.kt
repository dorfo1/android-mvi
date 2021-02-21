package com.example.hilt_sample.ui.detail

import com.example.hilt_sample.core.base.ViewAction
import com.example.hilt_sample.model.Rocket

sealed class DetailAction : ViewAction{
    data class DisplayRocket(val rocket: Rocket?) : DetailAction()
}