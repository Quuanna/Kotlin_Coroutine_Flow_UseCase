package com.anna.usecase_coroutine_and_test.usecase.flow.usecase3

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart

class FlowUseCase3ViewModel(
    private val repository: MockRepository
) : ViewModel() {

    val getPokemonList: LiveData<UiSateForFlow> = uiStateWithGetList()

    companion object {
        val factory = viewModelFactory {
            initializer {
                FlowUseCase3ViewModel(repository = MockRepositoryImpl(mockApi()))
            }
        }
    }

    private fun uiStateWithGetList() : LiveData<UiSateForFlow> {
       return repository.fetchPokemon()
            .map { pokemon ->
               UiSateForFlow.Success(pokemon) as UiSateForFlow
            }.onStart {
               emit(UiSateForFlow.Loading)
           }.onCompletion {
               Log.d("TEST", "Flow has completed.")
           }.catch { throwable ->
               Log.d("TEST", "Enter catch operator with $throwable")
               emit(UiSateForFlow.Error("something went wrong"))
           }.asLiveData()
    }

}
