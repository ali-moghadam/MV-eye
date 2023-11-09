package com.alirnp.mv_eye.api

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.StateFlow

/**
 * Interface for a type that produces a [StateFlow] of [State] by processing [Event]
 */
interface StateProducer<State : Any, Event : Any> {

    /**
     * State handling contract in presenter
     */
    val presenterHandler: PresenterHandler<State, Event>

    /**
     * The current value of [PresenterHandler.uiState].
     */
    val value: State

    /**
     * The previous values of [PresenterHandler.uiState].
     */
    val values: List<State>

    /**
     * Listens to events triggered by the user.
     */
    fun collectEvents(flowCollector: FlowCollector<Event>)

    /**
     * Updates the [State] atomically using the specified [function] of its value.
     *
     * [function] may be evaluated multiple times, if [State] is being concurrently updated.
     */
    fun update(function: (State) -> State)

    /**
     * Emits a [State] to this shared flow, suspending on buffer overflow if the shared flow was created
     * with the default [BufferOverflow.SUSPEND] strategy.
     *
     * This method is **thread-safe** and can be safely invoked from concurrent coroutines without
     * external synchronization.
     */
    fun emit(function: (State) -> State)
}