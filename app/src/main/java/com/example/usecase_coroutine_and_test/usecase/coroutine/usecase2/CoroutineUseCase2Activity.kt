package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase2

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

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
                is UiState.Loading -> binding.progressBar.isVisible = true
                is UiState.Success -> binding.progressBar.isVisible = false
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, uiState.errorMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}