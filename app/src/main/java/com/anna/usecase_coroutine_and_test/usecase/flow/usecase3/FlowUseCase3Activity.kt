package com.anna.usecase_coroutine_and_test.usecase.flow.usecase3

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.databinding.ActivityFlowUseCaseListBinding
import com.anna.usecase_coroutine_and_test.databinding.ActivityUseCaseBaseBinding
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Flow Exception Handling
 *  use case it network request first connect fetching Success，fetching second fail to use flow operator retry
 * `retry` operator retry failed network request、 `catch` operator to handle exceptions
 */

class FlowUseCase3Activity : AppCompatActivity() {

    private val binding by lazy { ActivityUseCaseBaseBinding.inflate(layoutInflater) }
    private val viewModel: FlowUseCase3ViewModel by viewModels { FlowUseCase3ViewModel.factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setupObserve()
    }

    private fun setupObserve() {
        viewModel.getPokemonList.observe(this) { uiState ->
            render(uiState)
        }
    }

    private fun render(uiState: UiSateForFlow) = with(binding) {
        when (uiState) {
            UiSateForFlow.Loading -> progressBar.visible()
            is UiSateForFlow.Error -> {
                tvText.text = uiState.message
                progressBar.gone()
            }

            is UiSateForFlow.Success -> {
                progressBar.gone()
                setupView(uiState.pokemonInfo)
            }
        }
    }

    private fun setupView(info: PokemonInfo) = with(binding) {
        imageView.load(info.imageUrl)
        tvText.text = info.name
    }
}