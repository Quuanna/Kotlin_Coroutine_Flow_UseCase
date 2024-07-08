package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase4

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import coil.load
import com.example.usecase_coroutine_and_test.R
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

/**
 * Perform network requests timeout Use suspending function `withTimeout()`
 */
class CoroutineUseCase4Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }

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
                is UiState.Loading -> binding.progressBar.isVisible = true
                is UiState.Success -> binding.progressBar.isVisible = false
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    Toast.makeText(this, uiState.errorMsg, Toast.LENGTH_LONG).show()
                }
            }
        }

        viewMode.pokemonInfo().observe(this) { info ->
            binding.tvName.text = info.name
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