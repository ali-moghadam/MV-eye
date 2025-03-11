package ir.snapp.fake

import ir.snapp.mveye.contract.MvEvent

sealed interface FakeEvent : MvEvent {
    object Increase : FakeEvent
    object Decrease : FakeEvent
    data class JumpTo(val count: Int) : FakeEvent
}