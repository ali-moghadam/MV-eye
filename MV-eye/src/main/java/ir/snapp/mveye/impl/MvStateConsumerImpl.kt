package ir.snapp.mveye.impl

import ir.snapp.mveye.api.MvStateConsumer
import ir.snapp.mveye.contract.MvEvent
import ir.snapp.mveye.contract.MvUiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MvStateConsumerImpl<S : MvUiState, E : MvEvent>(
    private val _uiState: MutableStateFlow<S>,
    private val _events: MutableSharedFlow<E>,
    private val scope: () -> CoroutineScope,
) : MvStateConsumer<S, E> {

    override val uiState: StateFlow<S>
        get() = _uiState.asStateFlow()

    override fun onEvent(event: E) {
        scope().launch {
            _events.emit(event)
        }
    }
}