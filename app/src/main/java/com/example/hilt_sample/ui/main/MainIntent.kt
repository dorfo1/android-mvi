package com.example.hilt_sample.ui.main

import com.example.hilt_sample.core.base.ViewIntent
import com.example.hilt_sample.model.Rocket

sealed class MainIntent : ViewIntent {

    object FetchRockets : MainIntent()
    data class FilterRockets(val params : String) : MainIntent()
    data class DetailRockets(val rocket : Rocket) : MainIntent()
    object ClearFilter : MainIntent()
}