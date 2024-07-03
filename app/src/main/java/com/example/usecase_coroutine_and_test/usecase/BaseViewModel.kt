package com.example.usecase_coroutine_and_test.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.usecase_coroutine_and_test.data.PokemonInfo

open class BaseViewModel<T> : ViewModel() {

    fun uiState(): LiveData<T> = uiState
    protected val uiState: MutableLiveData<T> = MutableLiveData()

    fun pokemonInfo(): LiveData<PokemonInfo> = pokemonInfo
    protected val pokemonInfo = MutableLiveData<PokemonInfo>()



}