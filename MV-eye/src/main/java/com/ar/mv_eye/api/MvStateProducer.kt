package com.ar.mv_eye.api

import com.ar.mv_eye.contract.MvEvent
import com.ar.mv_eye.contract.MvUiState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for a type that produces a [StateFlow] of [MvUiState] by processing [MvEvent]
 */
interface MvStateProducer<S : MvUiState, E : MvEvent> {

    /**
     * State handling contract in the ui
     */
    val mvStateManager: MvStateManager<S, E>

    /**
     * The current value of [mvStateManager.uiState].
     */
    val value: S

    /**
     * The previous values of [mvStateManager.uiState].
     */
    val values: List<S>

    /**
     * Listens to events triggered by the user.
     */
    fun collectEvents(flowCollector: FlowCollector<E>)

    /**
     * Updates the [MvUiState] atomically using the specified [function] of its value.
     *
     * [function] may be evaluated multiple times, if [MvUiState] is being concurrently updated.
     */
    fun update(function: (S) -> S)

    /**
     * Emits a [MvUiState] to this shared flow, suspending on buffer overflow if the shared flow was created
     * with the default [BufferOverflow.SUSPEND] strategy.
     *
     * This method is **thread-safe** and can be safely invoked from concurrent coroutines without
     * external synchronization.
     */
    fun emit(function: (S) -> S)
}