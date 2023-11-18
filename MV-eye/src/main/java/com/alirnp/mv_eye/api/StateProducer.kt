package com.alirnp.mv_eye.api

import com.alirnp.mv_eye.contract.Event
import com.alirnp.mv_eye.contract.UiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for a type that produces a [StateFlow] of [UiState] by processing [Event]
 */
interface StateProducer<S : UiState, E : Event> {

    /**
     * State handling contract in presenter
     */
    val presenterHandler: PresenterHandler<S, E>

    /**
     * The current value of [PresenterHandler.uiState].
     */
    val value: S

    /**
     * The previous values of [PresenterHandler.uiState].
     */
    val values: List<S>

    /**
     * Listens to events triggered by the user.
     */
    fun collectEvents(flowCollector: FlowCollector<E>)

    /**
     * Updates the [UiState] atomically using the specified [function] of its value.
     *
     * [function] may be evaluated multiple times, if [UiState] is being concurrently updated.
     */
    fun update(function: (S) -> S)

    /**
     * Emits a [UiState] to this shared flow, suspending on buffer overflow if the shared flow was created
     * with the default [BufferOverflow.SUSPEND] strategy.
     *
     * This method is **thread-safe** and can be safely invoked from concurrent coroutines without
     * external synchronization.
     */
    fun emit(function: (S) -> S)
}