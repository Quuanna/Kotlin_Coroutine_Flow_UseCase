package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.constant.DataSource
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineWithRoomUseCaseBinding
import com.anna.usecase_coroutine_and_test.utils.gone
import com.anna.usecase_coroutine_and_test.utils.setDrawable
import com.anna.usecase_coroutine_and_test.utils.visible

/**
 * Room and Coroutines Perform "offline-first"
 * Repository -> dataSource ->　local/network
 *
 * https://developer.android.com/topic/architecture/data-layer/offline-first
 */
class CoroutineUseCase7Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineWithRoomUseCaseBinding.inflate(layoutInflater) }

    private val viewModel: CoroutineUseCase7ViewModel by viewModels {
        CoroutineUseCase7ViewModel.factory(applicationContext)
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