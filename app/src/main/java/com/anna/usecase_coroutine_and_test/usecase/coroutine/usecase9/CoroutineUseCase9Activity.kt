package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase9

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCase9Binding
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.hideKeyboard
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible


/**
 * calculation is performed on a background thread using the default Dispatcher.
 * Cooperative cancellation use `isActive()`, `ensureActive()` or `yield()`
 *
 * Dispatchers.Default - for CPU power
 * Dispatchers.IO - for waiting (I/O)
 *
 * Dispatcher.Default vs Dispatcher.IO
 * https://medium.com/@mobiledev4you/dispatchers-default-vs-dispatchers-io-in-kotlin-coroutine-21a88e2fb41b
 */

class CoroutineUseCase9Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCase9Binding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase9ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()

        viewModel.uiState.observe(this) { uiState ->
            handlerUiState(uiState)
        }
    }

    private fun initView() = with(binding) {
        btnCalculate.setOnClickListener {
            editTextFactorialOf.text.toString().toIntOrNull()?.let {
                viewModel.performCalculation(it)
            }
        }

        btnCancel.setOnClickListener {
            viewModel.cancelCalculateUseYield()
//            lifecycleScope.launch {
//                viewModel.cancelCalculateUseDelay()
//            }
        }
    }

    private fun handlerUiState(uiState: UiState) = with(binding) {
        when (uiState) {
            UiState.Loading -> {
                progressBar.visible()
                textViewResult.text = ""
                textViewCalculationDuration.text = ""
                textViewStringConversionDuration.text = ""
                btnCalculate.isEnabled = false
                btnCancel.isEnabled = true
                textViewResult.hideKeyboard()
            }
            is UiState.Success -> {
                textViewCalculationDuration.text =
                    getString(R.string.duration_calculation, uiState.computationDuration)

                textViewStringConversionDuration.text =
                    getString(R.string.duration_stringconversion, uiState.stringConversionDuration)

                progressBar.gone()
                btnCalculate.isEnabled = true
                btnCancel.isEnabled = false

                textViewResult.text = if (uiState.result.length <= 150) {
                    uiState.result
                } else {
                    "${uiState.result.substring(0, 147)}..."
                }
            }

            is UiState.Error -> {
                progressBar.gone()
                btnCalculate.isEnabled = true
                btnCancel.isEnabled = false
                toast(uiState.message)
            }
        }
    }
}