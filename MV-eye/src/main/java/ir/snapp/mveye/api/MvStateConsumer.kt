package ir.snapp.mveye.api

import ir.snapp.mveye.contract.MvEvent
import ir.snapp.mveye.contract.MvUiState
import kotlinx.coroutines.flow.StateFlow

/**
 * A class which can sends user events and collect the UI state.
 */
interface MvStateConsumer<S: MvUiState, E : MvEvent> {
    /**
     * The Read-only state which describes the complete view state.
     */
    val uiState: StateFlow<S>

    /**
     * A representation of user’s interactions that changes the state of the UI.
     */
    fun onEvent(event: E)
}