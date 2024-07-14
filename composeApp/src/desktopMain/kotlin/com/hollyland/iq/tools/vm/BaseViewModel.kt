package com.hollyland.iq.tools.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<I : Intent> : ViewModel() {
    private val _uiIntent = MutableSharedFlow<I>(replay = 0, extraBufferCapacity = Int.MAX_VALUE)
    val uiIntent = _uiIntent.asSharedFlow()

    init {
        viewModelScope.launch {
            uiIntent.collect { intent ->
                handleIntent(intent)
            }
        }
    }

    abstract fun handleIntent(intent: I)

    fun dispatch(intent: I) {
        viewModelScope.launch {
            _uiIntent.emit(intent)
        }
    }
}

interface Intent