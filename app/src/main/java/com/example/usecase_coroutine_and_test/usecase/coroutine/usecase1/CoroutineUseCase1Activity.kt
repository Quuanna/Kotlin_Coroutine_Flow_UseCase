package com.example.usecase_coroutine_and_test.usecase.coroutine.usecase1

import android.os.Bundle
import android.util.Log
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
        setContentView(binding.root)

        case1ViewModel.pokemonList.observe(this@CoroutineUseCase1Activity) { pokemonList ->
            Log.d("TEST", pokemonList.toList().toString())
        }


        case1ViewModel.pokemonInfo.observe(this@CoroutineUseCase1Activity) { pokemonInfo ->
            Log.d("TEST", pokemonInfo.toString())
        }

        case1ViewModel.getPokemonList(25)
        case1ViewModel.getPokemonInfo("")
    }
}