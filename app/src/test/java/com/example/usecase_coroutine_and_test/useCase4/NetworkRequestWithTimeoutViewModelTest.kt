package com.example.usecase_coroutine_and_test.useCase4

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usecase_coroutine_and_test.FakeRepositoryImpl
import com.example.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase4.CoroutineUseCase4ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class NetworkRequestWithTimeoutViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `performNetworkRequest() should return Success UiState on successful network request within timeout`() =
        runTest {
            val responseDelay = 1000L // 模擬API運行時間
            val timeout = 1001L // 指定的逾時持續時間
            val fakeApi = FakeSuccessApi(responseDelay)
            val repository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase4ViewModel(repository)
            viewModel.observe()

            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.performNetworkRequest(timeout)

            advanceUntilIdle()

            Assert.assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
        }

    @Test
    fun `performNetworkRequest() should return Error UiState with timeout error message if timeout gets exceeded`() =
        runTest {
            val responseDelay = 1000L
            val timeout = 999L
            val fakeApi = FakeSuccessApi(responseDelay)
            val repository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase4ViewModel(repository)
            viewModel.observe()

            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.performNetworkRequest(timeout)

            advanceUntilIdle()

            Assert.assertEquals(
                listOf(
                    UiState.Loading,
                    UiState.Error("Network Request timed out!")
                ),
                receivedUiStates
            )
        }

    @Test
    fun `performNetworkRequest() should return Error UiState on unsuccessful network response`() =
        runTest {
            val responseDelay = 1000L
            val timeout = 1001L
            val fakeApi = FakeErrorApi(responseDelay)
            val repository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase4ViewModel(repository)
            viewModel.observe()

            Assert.assertTrue(receivedUiStates.isEmpty())

            viewModel.performNetworkRequest(timeout)

            advanceUntilIdle()

            Assert.assertEquals(
                listOf(
                    UiState.Loading,
                    UiState.Error("Network Request failed!")
                ),
                receivedUiStates
            )
        }

    private fun CoroutineUseCase4ViewModel.observe() {
        uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}