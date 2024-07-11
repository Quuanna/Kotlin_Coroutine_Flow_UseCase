package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding


/**
 * Room and Coroutines Perform "offline-first"
 *
 *
 * https://developer.android.com/topic/architecture/data-layer/offline-first
 */
class CoroutineUseCase7Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}