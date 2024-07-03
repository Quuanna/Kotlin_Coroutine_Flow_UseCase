package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase8

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.usecase_coroutine_and_test.R
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

class CoroutineUseCase8Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}