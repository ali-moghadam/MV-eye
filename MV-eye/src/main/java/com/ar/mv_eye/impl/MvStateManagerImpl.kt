package com.ar.mv_eye.impl

import com.ar.mv_eye.api.MvStateManager
import com.ar.mv_eye.contract.MvEvent
import com.ar.mv_eye.contract.MvUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MvStateManagerImpl<S : MvUiState, E : MvEvent>(
    initialState: S,
    private val scope: () -> CoroutineScope,
) : MvStateManager<S, E> {

    private val _events = MutableSharedFlow<E>()

    private val _uiState = MutableStateFlow(initialState)

    private val events: SharedFlow<E> = _events.asSharedFlow()

    override val value: S
        get() = _uiState.value

    override val stateConsumer = MvStateConsumerImpl(
        _uiState,
        _events,
        scope
    )

    override fun collectEvents(flowCollector: FlowCollector<E>) {
        scope().launch {
            events.collect(flowCollector)
        }
    }

    override fun update(function: (S) -> S) {
        _uiState.update(function)
    }

    override fun emit(function: (S) -> S) {
        scope().launch {
            val newState = function(stateConsumer.uiState.value)
            _uiState.emit(newState)
        }
    }
}