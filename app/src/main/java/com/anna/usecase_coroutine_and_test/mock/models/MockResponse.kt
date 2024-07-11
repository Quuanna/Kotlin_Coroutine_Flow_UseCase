package com.anna.usecase_coroutine_and_test.mock.models

data class MockResponse(
    val path: String,
    val body: () -> String,
    val status: Int,
    val delayInMs: Long,
    val persist: Boolean,
    val errorFrequencyInPercent: Int
)
