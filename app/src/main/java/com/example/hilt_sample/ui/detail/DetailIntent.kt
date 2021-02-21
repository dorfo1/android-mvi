package com.example.hilt_sample.ui.detail

import com.example.hilt_sample.core.base.ViewIntent
import com.example.hilt_sample.model.Rocket

sealed class DetailIntent : ViewIntent {
    data class DisplayRocket(val rocket: Rocket?) : DetailIntent()
}