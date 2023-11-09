package com.alirnp.mv_eye.impl

import com.alirnp.mv_eye.api.PresenterHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PresenterHandlerImpl<State, Event>(
    private val _uiState: MutableStateFlow<State>,
    private val _events: MutableSharedFlow<Event>,
    private val scope: () -> CoroutineScope,
) : PresenterHandler<State, Event> {

    override val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()

    override fun onEvent(event: Event) {
        scope().launch {
            _events.emit(event)
        }
    }
}