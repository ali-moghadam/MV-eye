package com.alirnp.mv_eye.impl

import com.alirnp.mv_eye.api.PresenterHandler
import com.alirnp.mv_eye.contract.Event
import com.alirnp.mv_eye.contract.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PresenterHandlerImpl<S : UiState, E : Event>(
    private val _uiState: MutableStateFlow<S>,
    private val _events: MutableSharedFlow<E>,
    private val scope: () -> CoroutineScope,
) : PresenterHandler<S, E> {

    override val uiState: StateFlow<S>
        get() = _uiState.asStateFlow()

    override fun onEvent(event: E) {
        scope().launch {
            _events.emit(event)
        }
    }
}