package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseTextBinding

class CoroutineUseCase3Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseTextBinding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase3ViewModel by viewModels { CoroutineUseCase3ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.getPokemonSequentially()
        viewModel.getPokemonConcurrently()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.sequentiallyResultOrder.observe(this@CoroutineUseCase3Activity) { map ->
            val stringBuilder = StringBuilder()
            binding.run {
                map.forEach { (key, value) ->
                    if (tvTextOne.text.isNotEmpty()) {
                        tvTextOne.text = stringBuilder.append("\n ").append(key).append("=").append(value)
                    } else {
                        tvTextOne.text = stringBuilder.append("Sequentially: \n ").append(key).append("=").append(value)
                    }
                }
            }
        }

        viewModel.concurrentlyResultOrder.observe(this@CoroutineUseCase3Activity) { map ->
            val stringBuilder = StringBuilder()

            binding.run {
                map.forEach { (key, value) ->
                    if (tvTextTwo.text.isNotEmpty()) {
                        tvTextTwo.text = stringBuilder.append("\n ").append(key).append("=").append(value)
                    } else {
                        tvTextTwo.text = stringBuilder.append("Concurrently: \n ").append(key).append("=").append(value)
                    }
                }
            }
        }


        viewModel.uiState().observe(this@CoroutineUseCase3Activity) { state ->
            when (state) {
                is UiState.Loading -> binding.progressBar.isVisible = true
                is UiState.Error, UiState.Success -> binding.progressBar.isVisible = false
            }
        }
    }
}