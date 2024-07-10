package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5

data class MockResponse(
    val path: String,
    val body: () -> String,
    val status: Int,
    val delayInMs: Long,
    val persist: Boolean,
    val errorFrequencyInPercent: Int
)
