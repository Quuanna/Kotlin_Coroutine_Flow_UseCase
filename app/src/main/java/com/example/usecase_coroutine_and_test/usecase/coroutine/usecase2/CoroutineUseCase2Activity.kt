package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase2

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding
import com.example.usecase_coroutine_and_test.utils.gone
import com.example.usecase_coroutine_and_test.utils.toast
import com.example.usecase_coroutine_and_test.utils.visible

/**
 * Perform two sequential network requests
 */
class CoroutineUseCase2Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    private val case1ViewModel: CoroutineUseCase2ViewModel by viewModels { CoroutineUseCase2ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
        case1ViewModel.getPokemonImage(1)
    }

    private fun setupObserve() {

        case1ViewModel.pokemonInfo().observe(this@CoroutineUseCase2Activity) { info ->
            binding.tvName.text = info.name
            binding.imageView.load(info.imageUrl)
        }

        case1ViewModel.uiState().observe(this@CoroutineUseCase2Activity) { uiState ->
            when (uiState) {
                is UiState.Loading -> binding.progressBar.visible()
                is UiState.Success -> binding.progressBar.gone()
                is UiState.Error -> {
                    binding.progressBar.gone()
                    toast(uiState.errorMsg)
                }
            }
        }
    }
}