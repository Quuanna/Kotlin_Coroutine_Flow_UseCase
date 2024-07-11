package com.example.usecase_coroutine_and_test.useCase1

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usecase_coroutine_and_test.FakeRepositoryImpl
import com.example.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase1.CoroutineUseCase1ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SingleNetworkRequestTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `viewModel注入假的ApiService和Repo後測試getPokemonList回傳成功狀態`() = runTest {
        val fakeApi = FakeSuccessApi()
        val fakeRepository = FakeRepositoryImpl(fakeApi)
        CoroutineUseCase1ViewModel(fakeRepository).apply {
            observe()
            assertTrue(receivedUiStates.isEmpty())
            getPokemonName(1)
        }
        assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
    }

    @Test
    fun `viewModel注入假的ApiService和Repo後測試getPokemonList回傳失敗狀態`() = runTest {
        val fakeApi = FakeErrorApi()
        val fakeRepository = FakeRepositoryImpl(fakeApi)
        CoroutineUseCase1ViewModel(fakeRepository).apply {
            observe()
            assertTrue(receivedUiStates.isEmpty())
            getPokemonName(1)
        }

        assertEquals(
            listOf(UiState.Loading, UiState.Error("Network Request failed!")), receivedUiStates
        )
    }

    private fun CoroutineUseCase1ViewModel.observe() {
        uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }

}