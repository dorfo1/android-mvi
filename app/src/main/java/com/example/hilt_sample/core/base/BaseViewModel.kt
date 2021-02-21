package com.example.hilt_sample.core.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch


abstract class BaseViewModel<INTENT : ViewIntent,ACTION : ViewAction,STATE : ViewState> : ViewModel() {

    private val _state = MutableLiveData<STATE>()
    val state : LiveData<STATE> get() = _state

    private val action = Channel<ACTION>(Channel.UNLIMITED)

    abstract fun dispatchIntent(intent : INTENT)

    abstract fun handleAction(action: ACTION)

    init {
        viewModelScope.launch {
            action.consumeAsFlow().collect {
                handleAction(it)
            }
        }
    }


    fun setAction(action: ACTION){
        viewModelScope.launch {
            this@BaseViewModel.action.send(action)
        }
    }

    fun setState(state : STATE){
        _state.postValue(state)
    }


}