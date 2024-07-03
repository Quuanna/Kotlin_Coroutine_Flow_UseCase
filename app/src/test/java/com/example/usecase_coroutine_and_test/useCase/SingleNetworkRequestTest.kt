package com.example.usecase_coroutine_and_test.useCase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1.CoroutineUseCase1ViewModel
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
        val viewModel = CoroutineUseCase1ViewModel(fakeRepository)
        observeViewModel(viewModel)
        assertTrue(receivedUiStates.isEmpty())

        viewModel.getPokemonList(1)
        assertEquals(listOf(UiState.Loading, UiState.Success), receivedUiStates)
    }

    private fun observeViewModel(viewModel: CoroutineUseCase1ViewModel) {
        viewModel.uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }

    @Test
    fun `viewModel注入假的ApiService和Repo後測試getPokemonList回傳失敗狀態`() = runTest {
        val fakeApi = FakeErrorApi()
        val fakeRepository = FakeRepositoryImpl(fakeApi)
        val viewModel = CoroutineUseCase1ViewModel(fakeRepository)
        observeViewModel(viewModel)

        assertTrue(receivedUiStates.isEmpty())

        viewModel.getPokemonList(1)

        assertEquals(
            listOf(UiState.Loading, UiState.Error("Network Request failed!")), receivedUiStates
        )
    }



}