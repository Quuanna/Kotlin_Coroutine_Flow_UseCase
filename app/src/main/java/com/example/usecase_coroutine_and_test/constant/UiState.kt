package com.example.usecase_coroutine_and_test.constant

sealed class UiStates {
    data object Loading: UiStates()
    data class Success(val msg: String): UiStates()
    data class Error(val msg: String): UiStates()

}