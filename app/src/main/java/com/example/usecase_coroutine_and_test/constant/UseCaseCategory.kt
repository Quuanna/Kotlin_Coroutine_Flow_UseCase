package com.example.usecase_coroutine_and_test.constant

import androidx.appcompat.app.AppCompatActivity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1.CoroutineUseCase1Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase2.CoroutineUseCase2Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase3.CoroutineUseCase3Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase4.CoroutineUseCase4Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5.CoroutineUseCase5Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase6.CoroutineUseCase6Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase7.CoroutineUseCase7Activity
import com.example.usecase_coroutine_and_test.usecase.coroutine.usecase8.CoroutineUseCase8Activity
import com.example.usecase_coroutine_and_test.usecase.flow.usecase1.FlowUseCase1Activity
import com.example.usecase_coroutine_and_test.usecase.flow.usecase2.FlowUseCase2Activity
import com.example.usecase_coroutine_and_test.usecase.flow.usecase3.FlowUseCase3Activity
import com.example.usecase_coroutine_and_test.usecase.flow.usecase4.FlowUseCase4Activity

data class UseCaseCategory(val useCases: MutableList<out Enum<*>>)

enum class CoroutineUseCaseType(val descriptor: String, val targetActivity: Class<out AppCompatActivity>) {
    USE_CASE_1("1. Perform single network request", CoroutineUseCase1Activity::class.java),
    USE_CASE_2("2. Perform two sequential network requests", CoroutineUseCase2Activity::class.java),
    USE_CASE_3("3. Perform network requests concurrently compare Sequentially run time", CoroutineUseCase3Activity::class.java),
    USE_CASE_4("4. Perform network requests timeout Use suspending function `withTimeout()`", CoroutineUseCase4Activity::class.java),
    USE_CASE_5("5. Perform retry network requests ", CoroutineUseCase5Activity::class.java),
    USE_CASE_6("6. Perform network requests timeout or retry", CoroutineUseCase6Activity::class.java),
    USE_CASE_7("7. Continue Coroutine execution when the user leaves the screen", CoroutineUseCase7Activity::class.java),
    USE_CASE_8("8. Using WorkManager with Coroutines", CoroutineUseCase8Activity::class.java),
}

enum class FlowUseCaseType(val descriptor: String, val targetActivity: Class<out AppCompatActivity>) {

    USE_CASE_1("1. Flow Basics", FlowUseCase1Activity::class.java),
    USE_CASE_2("2. Basic Flow Intermediate Operators", FlowUseCase2Activity::class.java),
    USE_CASE_3("3. Flow Exception Handling", FlowUseCase3Activity::class.java),
    USE_CASE_4("4. Exposing Flows in the ViewModel", FlowUseCase4Activity::class.java),
}