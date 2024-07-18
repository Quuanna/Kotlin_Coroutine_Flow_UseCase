package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase8

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Coroutines Exception Handling
 */
class CoroutineUseCase8Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase8ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 兩種方式
//        viewModel.handleExceptionWithTryCatch()

        viewModel.handleWithCoroutineExceptionHandler()

        viewModel.uiState().observe(this) { uiState ->
            setupUiState(uiState)
        }
    }

    private fun setupUiState(uiState: UiState) = with(binding) {
        when (uiState) {
            is UiState.Error -> {
                tvName.text = uiState.errorMsg
                progressBar.gone()
            }

            UiState.Loading -> progressBar.visible()
            UiState.Success -> progressBar.gone()
        }
    }


}