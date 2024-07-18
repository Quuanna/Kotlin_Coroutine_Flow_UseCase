package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase11

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCase11Binding

/**
 * Using WorkManager with Coroutines
 *
 * https://developer.android.com/develop/background-work/background-tasks/persistent/threading/coroutineworker
 */
class CoroutineUseCase11Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCase11Binding.inflate(layoutInflater) }
    private val viewModel: CoroutineUseCase11ViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel.performNetWorkRequest(this)

    }
}