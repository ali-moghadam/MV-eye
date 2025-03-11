package fake

import com.ar.mv_eye.contract.MvUiState

data class FakeUIState(
    val count: Int = 0,
) : MvUiState