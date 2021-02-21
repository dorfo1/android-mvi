package com.example.hilt_sample.ui.main

import com.example.hilt_sample.core.base.ViewAction
import com.example.hilt_sample.model.Rocket

sealed class MainAction : ViewAction {
    object FetchRockets : MainAction()
    data class FilterRockets(val params : String) : MainAction()
    data class DetailRockets(val rocket : Rocket) : MainAction()
    object ClearFilter : MainAction()
}