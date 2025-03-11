package fake

import com.ar.mv_eye.contract.MvEvent

sealed interface FakeEvent : MvEvent {
    object Increase : FakeEvent
    object Decrease : FakeEvent
    data class JumpTo(val count: Int) : FakeEvent
}