package com.anna.usecase_coroutine_and_test.usecase.flow.usecase4

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.core.api.PokemonCline
import com.anna.usecase_coroutine_and_test.usecase.flow.PokemonList
import com.anna.usecase_coroutine_and_test.usecase.flow.UiSateForFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.stateIn

class FlowUseCase4ViewModel(
    private val repository: PokemonListRepository
) : ViewModel() {

    companion object {
        val factory = viewModelFactory {
            initializer {
                FlowUseCase4ViewModel(
                    repository = PokemonListRepositoryImpl(PokemonCline.apiService)
                )
            }
        }
    }

    val getPokemonList: StateFlow<UiSateForFlow> = repository.fetchPokemon
        .map { list ->
            list.mapIndexed { index, item -> PokemonList(index, item.name) }
        }.map { list ->
            UiSateForFlow.Success(list) as UiSateForFlow
        }.onCompletion {
            Log.d("TEST", "Flow completion")
        }.catch {
            emit(UiSateForFlow.Error("Network request fail!"))
        }.stateIn(
            scope = viewModelScope,
            initialValue = UiSateForFlow.Loading,
            started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000)
        )
}