package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseTextBinding
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase3.CoroutineUseCase3ViewModel
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Perform network requests concurrently compare Sequentially run time
 */
class CoroutineUseCase3Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseTextBinding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase3ViewModel by viewModels { CoroutineUseCase3ViewModel.Factory }
    private var operationStartTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        viewModel.getPokemonSequentially()
        viewModel.getPokemonConcurrently()
        setupObserve()
    }

    private fun setupObserve() {
        viewModel.sequentiallyResultOrder.observe(this@CoroutineUseCase3Activity) { map ->
            binding.textViewDuration.text = getDuration()
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
            binding.textViewDuration2.text = getDuration()
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


        viewModel.uiState().observe(this@CoroutineUseCase3Activity) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    operationStartTime = System.currentTimeMillis()
                    binding.progressBar.visible()
                }
                is UiState.Success -> binding.progressBar.gone()
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    toast(uiState.errorMsg)
                }
            }
        }
    }

    private fun getDuration(): String {
        val duration = System.currentTimeMillis() - operationStartTime
       return getString(R.string.duration, duration)
    }
}