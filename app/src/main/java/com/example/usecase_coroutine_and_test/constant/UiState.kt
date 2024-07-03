package com.example.usecase_coroutine_and_test.constant

sealed class UiState {
    data object Loading: UiState()
    data object Success : UiState()
    data class Error(val errorMsg: String): UiState()

}