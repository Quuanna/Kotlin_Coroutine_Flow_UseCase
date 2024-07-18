package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7

import com.anna.usecase_coroutine_and_test.constant.DataSource

sealed class UiState {
    sealed class Loading : UiState() {
        data object LoadFromDB : Loading()
        data object LoadFromNetWork : Loading()
    }
    data class Success(val dataSource: DataSource, val message: String) : UiState()
    data class Error(val dataSource: DataSource, val message: String) : UiState()
}
