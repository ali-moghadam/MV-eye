package ir.snapp.mveye

import ir.snapp.mveye.api.MvStateConsumer
import ir.snapp.mveye.impl.MvStateManagerImpl
import ir.snapp.mveye.fake.FakeEvent
import ir.snapp.mveye.fake.FakeUIState
import ir.snapp.mveye.fake.FakeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class MvEyeTest {

    private lateinit var coroutineScope: CoroutineScope
    private lateinit var stateConsumer: MvStateConsumer<FakeUIState, FakeEvent>
    private lateinit var viewModel: FakeViewModel

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup() {
        coroutineScope = CoroutineScope(UnconfinedTestDispatcher())
        viewModel = FakeViewModel(
            coroutineScope = coroutineScope,
            stateManager = MvStateManagerImpl(FakeUIState(count = 10), { coroutineScope })
        )

        stateConsumer = viewModel.stateManager.stateConsumer
    }

    @Test
    fun `initial state has default counter value`() {
        val initializeCounterValue = 10
        assertNotNull(viewModel.stateManager.value.count)
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter increases by 1 when Increase event is called`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
        stateConsumer.onEvent(FakeEvent.Increase)
        assertEquals(11, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter increases correctly when Increase event is called multiple times`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
        stateConsumer.onEvent(FakeEvent.Increase)
        stateConsumer.onEvent(FakeEvent.Increase)
        stateConsumer.onEvent(FakeEvent.Increase)
        assertEquals(13, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter returns to default value when Increase and Decrease events are called sequentially`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
        stateConsumer.onEvent(FakeEvent.Increase)
        stateConsumer.onEvent(FakeEvent.Decrease)
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter decreases by 1 when Decrease event is called`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
        stateConsumer.onEvent(FakeEvent.Decrease)
        assertEquals(9, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter is set to specific value when JumpTo event is called`() {
        stateConsumer.onEvent(FakeEvent.JumpTo(11))
        assertEquals(11, viewModel.stateManager.value.count)
    }

    @Test
    fun `state is emitted correctly when emit() is called`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)

        viewModel.stateManager.emit { uiState ->
            uiState.copy(count = 11)
        }

        assertEquals(11, viewModel.stateManager.value.count)
    }

    @Test
    fun `state is updated correctly when update() is called`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)

        viewModel.stateManager.update { uiState ->
            uiState.copy(count = 11)
        }

        assertEquals(11, viewModel.stateManager.value.count)
    }

    @Test
    fun `counter updates correctly when JumpTo event is followed by Increase event`() {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)
        stateConsumer.onEvent(FakeEvent.JumpTo(20))
        stateConsumer.onEvent(FakeEvent.Increase)
        assertEquals(20 + 1, viewModel.stateManager.value.count)
    }

    @Test
    fun `state is updated correctly when emit() is called from multiple coroutines`() = runTest {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)

        // Launch multiple coroutines to update state concurrently
        val jobs = List(5) {
            launch {
                viewModel.stateManager.emit { uiState ->
                    uiState.copy(count = uiState.count + 1)
                }
            }
        }

        jobs.forEach { it.join() }

        assertEquals(initializeCounterValue + 5, viewModel.stateManager.value.count)
    }

    @Test
    fun `state is updated correctly when update() is called from multiple coroutines`() = runTest {
        val initializeCounterValue = 10
        assertEquals(initializeCounterValue, viewModel.stateManager.value.count)

        // Launch multiple coroutines to update state concurrently
        val jobs = List(5) {
            launch {
                viewModel.stateManager.update { uiState ->
                    uiState.copy(count = uiState.count + 1)
                }
            }
        }

        jobs.forEach { it.join() }

        assertEquals(initializeCounterValue + 5, viewModel.stateManager.value.count)
    }

}