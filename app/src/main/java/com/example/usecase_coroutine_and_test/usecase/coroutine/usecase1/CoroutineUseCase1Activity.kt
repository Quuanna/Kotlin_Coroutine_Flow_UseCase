package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding
import com.example.usecase_coroutine_and_test.utils.gone
import com.example.usecase_coroutine_and_test.utils.visible

/**
 * single request network
 */

class CoroutineUseCase1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    private val case1ViewModel: CoroutineUseCase1ViewModel by viewModels { CoroutineUseCase1ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
        case1ViewModel.getPokemonName(1)
    }

    private fun setupObserve() {
        case1ViewModel.uiState().observe(this@CoroutineUseCase1Activity) { uiState ->
            when (uiState) {
                is UiState.Loading -> binding.progressBar.visible()
                is UiState.Success, is UiState.Error -> binding.progressBar.gone()
            }
        }

        case1ViewModel.pokemonInfo().observe(this@CoroutineUseCase1Activity) { info ->
            binding.tvName.text = info.name
        }
    }

}