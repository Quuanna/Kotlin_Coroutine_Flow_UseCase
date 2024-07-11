package com.anna.usecase_coroutine_and_test.useCase2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.anna.usecase_coroutine_and_test.FakeRepositoryImpl
import com.anna.usecase_coroutine_and_test.ReplaceMainDispatcherRule
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase2.CoroutineUseCase2ViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SequentialNetworkRequestTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get: Rule
    val replaceMainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `should return Success when both network requests are successful`() = runTest {
        val fakeApi = FakeSuccessApi()
        val repository = FakeRepositoryImpl(fakeApi)
        val viewModel = CoroutineUseCase2ViewModel(repository)
        viewModel.observe()
        Assert.assertTrue(receivedUiStates.isEmpty())

        viewModel.getPokemonImage(1)
        Assert.assertEquals(
            listOf(UiState.Loading, UiState.Success),
            receivedUiStates
        )
    }


    @Test
    fun `should return Error when first network requests fails`() = runTest {
        val fakeApi = FakeErrorApi()
        val repository = FakeRepositoryImpl(fakeApi)
        val viewModel = CoroutineUseCase2ViewModel(repository)
        viewModel.observe()
        Assert.assertTrue(receivedUiStates.isEmpty())

        viewModel.getPokemonImage(1)
        Assert.assertEquals(
            listOf(UiState.Loading, UiState.Error()),
            receivedUiStates
        )
    }


    private fun CoroutineUseCase2ViewModel.observe() {
        uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}