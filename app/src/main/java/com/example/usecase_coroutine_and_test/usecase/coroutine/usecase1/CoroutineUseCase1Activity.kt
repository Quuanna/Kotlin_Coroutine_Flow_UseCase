package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCase1Binding

/**
 * single request network
 */

class CoroutineUseCase1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCase1Binding.inflate(layoutInflater) }
    private val case1ViewModel: CoroutineUseCase1ViewModel by viewModels { CoroutineUseCase1ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
        case1ViewModel.getPokemonList(1)
    }

    private fun setupObserve() {
        case1ViewModel.pokemonList.observe(this@CoroutineUseCase1Activity) { pokemonList ->
            case1ViewModel.getPokemonInfo(pokemonList.results.first().name)
        }

        case1ViewModel.pokemonInfo.observe(this@CoroutineUseCase1Activity) { pokemonInfo ->
            binding.run {
                imageView.load(pokemonInfo.sprites.other.home.front_default)
                imageView2.load(pokemonInfo.sprites.other.home.front_shiny)
            }
        }

        case1ViewModel.uiState().observe(this@CoroutineUseCase1Activity) { uiState ->
            when(uiState) {
                is UiState.Loading -> binding.progressBar.isVisible = true
                is UiState.Success, is UiState.Error -> binding.progressBar.isVisible = false
            }
        }
    }

}