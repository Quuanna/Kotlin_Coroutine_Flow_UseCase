package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase9

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.math.BigInteger
import kotlin.coroutines.cancellation.CancellationException
import kotlin.system.measureTimeMillis

class CoroutineUseCase9ViewModel(
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : ViewModel() {

    val uiState: LiveData<UiState> get() = _uiState
    private val _uiState: MutableLiveData<UiState> = MutableLiveData()

    private var calculateJob: Job? = null

    fun performCalculation(factorialOf: Int) {
        _uiState.value = UiState.Loading
        calculateJob = viewModelScope.launch {
            try {
                var result: BigInteger
                val computationDuration = measureTimeMillis {
                    result = calculateFactorial(factorialOf)
                }

                var resultString: String
                val stringConversionDuration = measureTimeMillis {
                    resultString = convertToString(result)
                }

                _uiState.value =
                    UiState.Success(resultString, computationDuration, stringConversionDuration)

            } catch (exception: Exception) {
                _uiState.value = if (exception is CancellationException) {
                    UiState.Error("Calculation was cancelled")
                } else {
                    UiState.Error("Error while calculating result")
                }
            }
        }
    }

    fun cancelCalculateUseYield() {
        calculateJob?.cancel()
    }

    suspend fun cancelCalculateUseDelay() {
        delay(1000) // 指定秒數後停止
        calculateJob?.cancel()
    }

    private suspend fun calculateFactorial(number: Int): BigInteger =
        withContext(defaultDispatcher) {
            var factorial = BigInteger.ONE
            for (i in 1..number) {

                yield() // 檢查到執行cancel馬上拋出cancellationException
//                delay(2000) // 延遲運行時間
                factorial = factorial.multiply(BigInteger.valueOf(i.toLong()))
            }
            factorial
        }

    private suspend fun convertToString(number: BigInteger): String =
        withContext(defaultDispatcher) {
            number.toString()
        }
}