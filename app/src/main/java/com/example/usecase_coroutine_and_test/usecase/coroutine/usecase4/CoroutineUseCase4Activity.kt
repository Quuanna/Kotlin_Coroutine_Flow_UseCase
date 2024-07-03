package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase4

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.usecase_coroutine_and_test.R
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

class CoroutineUseCase4Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}