package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase10

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.constant.DataSource
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineWithRoomUseCaseBinding
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7.UiState
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.setDrawable
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Using Room and Coroutines Perform "offline-first"，Continue Coroutine execution when the user leaves the screen
 *
 * 離開當前 viewModel時會被結束，所以viewModeScope也會被結束，當`ViewModel` 被清除，並且在 `viewModelScope` 中啟動的所有協程都被取消。然而有時希望繼續執行某個協程操作
 * 當使用者離開螢幕時。在此用例中，網路請求繼續運行，結果仍會插入資料庫中
 * 當使用者離開螢幕時進行快取，因為不想取消已經啟動的後台「cache sync」。
 */

class CoroutineUseCase10Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineWithRoomUseCaseBinding.inflate(layoutInflater) }

    private val viewModel: CoroutineUseCase10ViewModel by viewModels {
        CoroutineUseCase10ViewModel.factory(context = this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupObserve()
        initView()
    }

    private fun setupObserve() {
        viewModel.uiState.observe(this) { uiState ->
            when (uiState) {
                is UiState.Loading -> onLoad(uiState)
                is UiState.Error -> onError(uiState)
                is UiState.Success -> onSuccess(uiState)
            }
        }

        viewModel.pokemonInfo.observe(this) { info ->
            binding.tvText.text = info.name
            binding.imageView.load(info.imageUrl)
        }
    }

    private fun onLoad(uiState: UiState.Loading) = with(binding) {
        when (uiState) {
            UiState.Loading.LoadFromDB -> {
                progressBarLoadFromDb.visible()
                imageViewDatabaseLoadSuccessOrError.gone()
            }

            UiState.Loading.LoadFromNetWork -> {
                progressBarLoadFromNetwork.visible()
                imageViewNetworkLoadSuccessOrError.gone()
            }
        }
    }

    private fun onSuccess(uiState: UiState.Success) = with(binding) {
        when (uiState.dataSource) {
            DataSource.DATABASE -> {
                progressBarLoadFromDb.gone()
                imageViewDatabaseLoadSuccessOrError.visible()
                imageViewDatabaseLoadSuccessOrError.setDrawable(R.drawable.ic_check_green_24dp)
                textViewLoadFromDatabase.visible()
                textViewLoadFromDatabase.text = uiState.message
            }

            DataSource.NETWORK -> {
                progressBarLoadFromNetwork.gone()
                imageViewNetworkLoadSuccessOrError.visible()
                imageViewNetworkLoadSuccessOrError.setDrawable(R.drawable.ic_check_green_24dp)
                textViewLoadFromNetwork.visible()
                textViewLoadFromNetwork.text = uiState.message
            }
        }
    }

    private fun onError(uiState: UiState.Error) = with(binding) {
        when (uiState.dataSource) {
            DataSource.DATABASE -> {
                progressBarLoadFromDb.gone()
                imageViewDatabaseLoadSuccessOrError.visible()
                imageViewDatabaseLoadSuccessOrError.setDrawable(R.drawable.ic_clear_red_24dp)
                textViewLoadFromDatabase.visible()
                textViewLoadFromDatabase.text = uiState.message
            }

            DataSource.NETWORK -> {
                progressBarLoadFromNetwork.gone()
                imageViewNetworkLoadSuccessOrError.visible()
                imageViewNetworkLoadSuccessOrError.setDrawable(R.drawable.ic_clear_red_24dp)
                textViewLoadFromNetwork.visible()
                textViewLoadFromNetwork.text = uiState.message
            }
        }
    }

    private fun initView() {
        binding.buttonGetData.setOnClickListener {
            viewModel.performFetchData()
        }

        binding.buttonClearDataBase.setOnClickListener {
            viewModel.clearDatabase()
        }

    }
}