package fake

import com.ar.mv_eye.api.MvStateManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class FakeViewModel(
    coroutineScope: CoroutineScope,
    val stateManager: MvStateManager<FakeUIState, FakeEvent>,
) {

    init {
        coroutineScope.launch {
            stateManager.collectEvents { event ->
                when (event) {
                    is FakeEvent.Increase -> stateManager.update { uiState ->
                        uiState.copy(count = uiState.count + 1)
                    }

                    is FakeEvent.Decrease -> stateManager.update { uiState ->
                        uiState.copy(count = uiState.count - 1)
                    }

                    is FakeEvent.JumpTo -> stateManager.update { uiState ->
                        uiState.copy(count = event.count)
                    }
                }
            }
        }
    }
}