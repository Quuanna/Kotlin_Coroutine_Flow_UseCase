package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase6

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseImageBinding
import com.example.usecase_coroutine_and_test.utils.gone
import com.example.usecase_coroutine_and_test.utils.toast
import com.example.usecase_coroutine_and_test.utils.visible


/**
 * Perform network requests timeout or retry
 */
class CoroutineUseCase6Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseImageBinding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase6VIewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.networkPerformRetryAndTimeout()

        viewModel.uiState().observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> binding.progressBar.visible()
                is UiState.Success -> binding.progressBar.visible()
                is UiState.Error -> {
                    binding.progressBar.gone()
                    toast(uiState.errorMsg)
                }
            }
        }

        viewModel.pokemonInfoList.observe(this) { infoList ->
            binding.tvName.text = infoList.first().name
            binding.imageView.load(infoList.first().imageUrl)
            binding.tvName2.text = infoList.last().name
            binding.imageView2.load(infoList.last().imageUrl)
        }
    }
}