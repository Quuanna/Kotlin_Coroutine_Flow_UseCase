package com.example.usecase_coroutine_and_test.useCase3

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usecase_coroutine_and_test.FakeRepositoryImpl
import com.example.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase3.CoroutineUseCase3ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.currentTime
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class ConcurrentlyNetWorkRequestTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `perform Network Requests Sequentially should return data after 3 times the response delay`() =
        runTest {  // this: TestScope
            val responseDelay = 1000L
            val fakeApi = FakeSuccessApi(responseDelay)
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.getPokemonSequentially()
            advanceUntilIdle() // execution order of your coroutines is guaranteed，Runs the new coroutine，Let the coroutine complete and changes propagate
            Assert.assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
            // 驗證請求是否確實按順序執行並且接收所有資料花費了 3000 毫秒
            Assert.assertEquals(3000, currentTime)
        }

    @Test
    fun `perform Network Requests Concurrently should return data after the response delay`() =
        runTest {  // this: TestScope
            val responseDelay = 1000L
            val fakeApi = FakeSuccessApi(responseDelay)
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.getPokemonConcurrently()
            advanceUntilIdle() // execution order of your coroutines is guaranteed，Runs the new coroutine，Let the coroutine complete and changes propagate
            Assert.assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
            // 驗證請求是否確實在 1000 毫秒內並發執行
            Assert.assertEquals(1000, currentTime)
        }

    @Test
    fun `performNetwork Requests Concurrently should return Error when network request fails`() =
        runTest {  // this: TestScope
            val responseDelay = 1000L
            val fakeApi = FakeErrorApi(responseDelay)
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.getPokemonConcurrently()
            advanceUntilIdle()  // execution order of your coroutines is guaranteed，Runs the new coroutine， Let the coroutine complete and changes propagate
            Assert.assertEquals(listOf(UiState.Loading, UiState.Error()), receivedUiStates)
        }

    private fun CoroutineUseCase3ViewModel.observe() {
        uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}