package com.anna.usecase_coroutine_and_test.constant

import androidx.appcompat.app.AppCompatActivity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase10.CoroutineUseCase10Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase9.CoroutineUseCase9Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase1.CoroutineUseCase1Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase11.CoroutineUseCase11Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase2.CoroutineUseCase2Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase3.CoroutineUseCase3Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase4.CoroutineUseCase4Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase5.CoroutineUseCase5Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase6.CoroutineUseCase6Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7.CoroutineUseCase7Activity
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase8.CoroutineUseCase8Activity
import com.anna.usecase_coroutine_and_test.usecase.flow.usecase1.FlowUseCase1Activity
import com.anna.usecase_coroutine_and_test.usecase.flow.usecase2.FlowUseCase2Activity
import com.anna.usecase_coroutine_and_test.usecase.flow.usecase3.FlowUseCase3Activity
import com.anna.usecase_coroutine_and_test.usecase.flow.usecase4.FlowUseCase4Activity

data class UseCaseCategory(val useCases: MutableList<out Enum<*>>)

enum class CoroutineUseCaseType(val descriptor: String, val targetActivity: Class<out AppCompatActivity>) {
    USE_CASE_1("1. Perform single network request", CoroutineUseCase1Activity::class.java),
    USE_CASE_2("2. Perform two sequential network requests", CoroutineUseCase2Activity::class.java),
    USE_CASE_3("3. Perform network requests concurrently compare Sequentially run time", CoroutineUseCase3Activity::class.java),
    USE_CASE_4("4. Perform network requests timeout Use suspending function `withTimeout()`、`withTimeoutOrNull()`", CoroutineUseCase4Activity::class.java),
    USE_CASE_5("5. Perform retry network requests ", CoroutineUseCase5Activity::class.java),
    USE_CASE_6("6. Perform network requests retry With Timeout", CoroutineUseCase6Activity::class.java),
    USE_CASE_7("7. Using Room and Coroutines Perform offline-first", CoroutineUseCase7Activity::class.java),
    USE_CASE_8("8. Coroutines Exception Handling", CoroutineUseCase8Activity::class.java),
    USE_CASE_9("9. Dispatcher with cooperative cancellation", CoroutineUseCase9Activity::class.java),
    USE_CASE_10("10. Using Room and Coroutines Perform offline-first，Continue Coroutine execution when the user leaves the screen ", CoroutineUseCase10Activity::class.java),
    USE_CASE_11("11. Using WorkManager with Coroutines", CoroutineUseCase11Activity::class.java);

}

enum class FlowUseCaseType(val descriptor: String, val targetActivity: Class<out AppCompatActivity>) {

    USE_CASE_1("1. Flow Basics", FlowUseCase1Activity::class.java),
    USE_CASE_2("2. Basic Flow Intermediate Operators", FlowUseCase2Activity::class.java),
    USE_CASE_3("3. Flow Exception Handling", FlowUseCase3Activity::class.java),
    USE_CASE_4("4. Exposing Flows in the ViewModel", FlowUseCase4Activity::class.java),
}