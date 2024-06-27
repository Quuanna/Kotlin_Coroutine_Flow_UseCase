package com.example.usecase_coroutine_and_test.data

enum class CoroutineUseCaseType(val text: String) {

    USE_CASE_1("1. Perform single network request"),
    USE_CASE_2("2. Perform two **sequential** network requests"),
    USE_CASE_3("3. Perform network requests concurrently"),
    USE_CASE_4("4. Perform Network requests timeout or retry"),
    USE_CASE_5("5. Room and Coroutines Perform offline-first"),
    USE_CASE_6("6. Coroutines Exception Handling"),
    USE_CASE_7("7. Continue Coroutine execution when the user leaves the screen"),
    USE_CASE_8("8. Using WorkManager with Coroutines"),
}