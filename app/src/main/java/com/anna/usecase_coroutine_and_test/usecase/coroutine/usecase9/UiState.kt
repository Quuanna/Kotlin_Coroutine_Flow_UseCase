package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase9

sealed class UiState {
    data object Loading : UiState()
    data class Success(
        val result: String,
        val computationDuration: Long,
        val stringConversionDuration: Long
    ) : UiState()

    data class Error(val message: String) : UiState()
}