package ir.snapp.fake

import ir.snapp.mveye.contract.MvUiState

data class FakeUIState(
    val count: Int = 0,
) : MvUiState