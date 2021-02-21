package com.example.hilt_sample.core.base


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class UseCase<in T,out O> : CoroutineScope{

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.IO

    suspend operator fun invoke(params : T): O{
        return execute(params)
    }

    abstract suspend fun execute(params: T): O


}