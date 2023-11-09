package ali.rnp.mv_eye.api

import kotlinx.coroutines.flow.StateFlow

/**
 * A class which can sends user events and collect the UI state.
 */
interface PresenterHandler<State, Event> {
    /**
     * The Read-only state which describes the complete view state.
     */
    val uiState: StateFlow<State>

    /**
     * A representation of user’s interactions that changes the state of the UI.
     */
    fun onEvent(event: Event)
}