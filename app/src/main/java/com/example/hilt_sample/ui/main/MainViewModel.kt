package com.example.hilt_sample.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.viewModelScope
import com.example.hilt_sample.core.base.BaseViewModel
import com.example.hilt_sample.core.base.Resource
import com.example.hilt_sample.data.repository.RocketRepository
import com.example.hilt_sample.model.Rocket
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.*

class MainViewModel @ViewModelInject constructor(
    private val rocketRepository: RocketRepository
) : BaseViewModel<MainIntent, MainAction, MainState>() {

    private var cacheRockets: List<Rocket> = emptyList()

    override fun dispatchIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.FetchRockets -> setAction(MainAction.FetchRockets)
            is MainIntent.FilterRockets -> setAction(MainAction.FilterRockets(intent.params))
            is MainIntent.DetailRockets -> setAction(MainAction.DetailRockets(intent.rocket))
            is MainIntent.ClearFilter -> setAction(MainAction.ClearFilter)
        }
    }

    override fun handleAction(action: MainAction) {
        when (action) {
            is MainAction.FetchRockets -> {
                viewModelScope.launch {
                    if (cacheRockets.isNotEmpty())
                        setState(MainState.ResultRockets(cacheRockets))
                    else
                        rocketRepository.getRockets().collect {
                            when(it){
                                is Resource.Success -> {
                                    cacheRockets = it.data ?: emptyList()
                                    setState(MainState.ResultRockets(it.data ?: emptyList()))
                                }
                                is Resource.Loading -> setState(MainState.LoadingState)
                                is Resource.Error -> setState(MainState.Error(it.exception ?: Exception()))
                            }
                        }
                }
            }
            is MainAction.FilterRockets -> {
                setState(MainState.ResultRockets(cacheRockets.filter { it.name.toLowerCase(Locale.getDefault()).contains(action.params.toLowerCase(Locale.getDefault())) }))
            }
            is MainAction.DetailRockets -> {
                setState(MainState.RocketDetail(action.rocket))
            }
            is MainAction.ClearFilter -> {
                setState(MainState.ResultRockets(cacheRockets))
            }
        }
    }


}