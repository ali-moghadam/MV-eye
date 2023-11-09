package com.alirnp.mv_eye.impl

import com.alirnp.mv_eye.api.StateProducer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class StateProducerImpl<State : Any, Event : Any>(
    initialState: State,
    private val scope: () -> CoroutineScope,
) : StateProducer<State, Event> {

    private val _events = MutableSharedFlow<Event>()

    private val _uiState = MutableStateFlow(initialState)

    private val events: SharedFlow<Event> = _events.asSharedFlow()

    override val value: State
        get() = _uiState.value

    override val values: List<State>
        get() = _uiState.replayCache

    override val presenterHandler = PresenterHandlerImpl(
        _uiState,
        _events,
        scope
    )

    override fun collectEvents(flowCollector: FlowCollector<Event>) {
        scope().launch {
            events.collect(flowCollector)
        }
    }

    override fun update(function: (State) -> State) {
        _uiState.update(function)
    }

    override fun emit(function: (State) -> State) {
        scope().launch {
            val newState = function(presenterHandler.uiState.value)
            _uiState.emit(newState)
        }
    }
}