package com.example.usecase_coroutine_and_test.useCase3

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usecase_coroutine_and_test.FakeRepositoryImpl
import com.example.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase3.CoroutineUseCase3ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
    fun `performNetworkRequestsSequentially should return response`() =
        runTest {
            val fakeApi = FakeSuccessApi()
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())
            viewModel.getPokemonSequentially()
            Assert.assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
        }

    @Test
    fun `performNetworkRequestsConcurrently should return response `() =
        runTest {
            val fakeApi = FakeSuccessApi()
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())
            viewModel.getPokemonConcurrently()
            Assert.assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
        }

    @Test
    fun `performNetworkRequestsConcurrently should return fails`() =
        runTest {
            val fakeApi = FakeErrorApi()
            val fakeRepository = FakeRepositoryImpl(fakeApi)
            val viewModel = CoroutineUseCase3ViewModel(fakeRepository)
            viewModel.observe()
            Assert.assertTrue(receivedUiStates.isEmpty())
            viewModel.getPokemonConcurrently()
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