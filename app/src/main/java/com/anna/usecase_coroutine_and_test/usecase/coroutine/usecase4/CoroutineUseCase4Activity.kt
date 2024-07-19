package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase4

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseTimeoutBinding
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.toast
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Perform network requests timeout Use suspending function `withTimeout()`
 */
class CoroutineUseCase4Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseTimeoutBinding.inflate(layoutInflater) }

    private val viewMode: CoroutineUseCase4ViewModel by viewModels { CoroutineUseCase4ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        setupObserve()
        initView()
    }

    private fun setupObserve() {
        viewMode.uiState().observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> binding.progressBar.visible()
                is UiState.Success -> binding.progressBar.gone()
                is UiState.Error -> {
                    binding.progressBar.gone()
                    toast(uiState.errorMsg)
                }
            }
        }

        viewMode.pokemonInfo().observe(this) { info ->
            binding.tvText.text = info.name
            binding.imageView.load(info.imageUrl)
        }

    }

    private fun initView() {
        binding.button.setOnClickListener {
            val timeout = binding.editTextTimeOut.text.toString().toLongOrNull()
            if (timeout != null) {
                viewMode.performNetworkRequest(timeout = timeout)
            }
        }
    }
}