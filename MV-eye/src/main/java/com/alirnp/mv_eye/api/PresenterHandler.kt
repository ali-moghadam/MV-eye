package com.alirnp.mv_eye.api

import com.alirnp.mv_eye.contract.Event
import com.alirnp.mv_eye.contract.UiState
import kotlinx.coroutines.flow.StateFlow

/**
 * A class which can sends user events and collect the UI state.
 */
interface PresenterHandler<S: UiState, E : Event> {
    /**
     * The Read-only state which describes the complete view state.
     */
    val uiState: StateFlow<S>

    /**
     * A representation of user’s interactions that changes the state of the UI.
     */
    fun onEvent(event: E)
}