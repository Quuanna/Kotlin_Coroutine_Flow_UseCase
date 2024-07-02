package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.viewmodel.ViewModelFactoryDsl
import com.example.usecase_coroutine_and_test.R
import com.example.usecase_coroutine_and_test.databinding.ActivityCoroutineUseCase1Binding

class CoroutineUseCase1Activity : AppCompatActivity() {

    private val binding by lazy { ActivityCoroutineUseCase1Binding.inflate(layoutInflater) }
    private val case1ViewModel: CoroutineUseCase1ViewModel by viewModels { CoroutineUseCase1ViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_use_case1)


    }
}