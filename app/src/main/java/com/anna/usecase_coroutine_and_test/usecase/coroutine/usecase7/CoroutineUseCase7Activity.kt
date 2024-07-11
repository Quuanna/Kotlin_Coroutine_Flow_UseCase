package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.anna.usecase_coroutine_and_test.R
import com.anna.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCaseBinding

class CoroutineUseCase7Activity : AppCompatActivity() {
    private val binding by lazy { ActivityCoroutineUseCaseBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}