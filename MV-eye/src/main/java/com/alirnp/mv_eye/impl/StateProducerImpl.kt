package com.alirnp.mv_eye.impl

import com.alirnp.mv_eye.api.StateProducer
import com.alirnp.mv_eye.contract.Event
import com.alirnp.mv_eye.contract.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StateProducerImpl<S : UiState, E : Event>(
    initialState: S,
    private val scope: () -> CoroutineScope,
) : StateProducer<S, E> {

    private val _events = MutableSharedFlow<E>()

    private val _uiState = MutableStateFlow(initialState)

    private val events: SharedFlow<E> = _events.asSharedFlow()

    override val value: S
        get() = _uiState.value

    override val values: List<S>
        get() = _uiState.replayCache

    override val presenterHandler = PresenterHandlerImpl(
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
            val newState = function(presenterHandler.uiState.value)
            _uiState.emit(newState)
        }
    }
}