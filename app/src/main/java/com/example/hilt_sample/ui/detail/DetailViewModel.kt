package com.example.hilt_sample.ui.detail

import com.example.hilt_sample.core.base.BaseViewModel

class DetailViewModel : BaseViewModel<DetailIntent,DetailAction,DetailState>() {


    override fun dispatchIntent(intent: DetailIntent) {
        when(intent){
            is DetailIntent.DisplayRocket -> setAction(DetailAction.DisplayRocket(intent.rocket))
        }
    }

    override fun handleAction(action: DetailAction) {
        when(action){
            is DetailAction.DisplayRocket -> {
                action.rocket?.let {
                    setState(DetailState.Success(it))
                } ?: kotlin.run {
                    setState(DetailState.Error)
                }
            }
        }
    }
}