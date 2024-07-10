package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase5

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import coil.load
import com.example.usecase_coroutine_and_test.constant.UiState
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

/**
 * Perform retrying network requests
 *有時暫時性錯誤，常見三種情景(1)網路不穩逾時(2)伺服器端錯誤(3)速率限制和暫時過載(https://www.wangyuyun.com/news/content/2726.html)
 *
 * 重試次數作法考量
 * 1. 定義指定的 Http code，ex:500、504跟Server有關的可能是暫時性問題、冪等性考量 Http method(例如，GET、PUT、DELETE 通常是冪等的）
 * 3. 最大重試的次數，避免無線循環
 * 4. 重試的間隔時間，避免伺服器和網路不堪重負
 *
 * https://medium.com/@API4AI/best-practice-implementing-retry-logic-in-http-api-clients-0b5469c08ced
 */
class CoroutineUseCase5Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase5ViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // observe
        viewModel.uiState().observe(this) { uiState ->
            when(uiState) {
                is UiState.Loading -> binding.progressBar.isVisible = true
                is UiState.Success -> binding.progressBar.isVisible = false
                is UiState.Error -> {
                    binding.progressBar.isVisible = false
                    binding.tvName.text = uiState.errorMsg
                }
            }
        }

        viewModel.pokemonInfo().observe(this) { info ->
            binding.tvName.text = info.name
            binding.imageView.load(info.imageUrl)
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.performNetworkRequest()
    }
}