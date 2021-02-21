package com.example.hilt_sample.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

abstract class BaseActivity<INTENT: ViewIntent,ACTION:ViewAction,STATE: ViewState,VM : BaseViewModel<INTENT,ACTION,STATE>> : AppCompatActivity() {

    abstract fun getViewModel() : VM

    abstract fun handleState(it: STATE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    private fun setupObservers() {
        getViewModel().state.observe(this, Observer {
            handleState(it)
        })
    }

    fun dispatchIntent(intent: INTENT){
        getViewModel().dispatchIntent(intent)
    }
}