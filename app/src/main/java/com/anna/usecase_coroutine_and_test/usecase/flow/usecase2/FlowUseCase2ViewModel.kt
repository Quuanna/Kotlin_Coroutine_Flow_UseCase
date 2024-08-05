package com.anna.usecase_coroutine_and_test.usecase.flow.usecase2

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.core.api.PokemonCline
import com.anna.usecase_coroutine_and_test.usecase.flow.PokemonList
import com.anna.usecase_coroutine_and_test.usecase.flow.UiSateForFlow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class FlowUseCase2ViewModel(
    private val repository: PokemonListRepository,
    private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    val getPokemonList: LiveData<UiSateForFlow> = uiSateForFlowLiveData()

    companion object {
        val factory = viewModelFactory {
            initializer {
                FlowUseCase2ViewModel(
                    repository = PokemonListRepositoryImpl(PokemonCline.apiService),
                    dispatcher = Dispatchers.IO
                )
            }
        }
    }


    private fun uiSateForFlowLiveData(): LiveData<UiSateForFlow> {
        return repository.fetchPokemon
            .map { list ->
                list.mapIndexed { index, item -> PokemonList(index, item.name) }
            }.map { list ->
                UiSateForFlow.Success(list) as UiSateForFlow
            }.onStart {
                emit(UiSateForFlow.Loading)
            }.catch {
                emit(UiSateForFlow.Error("Network request fail!"))
            }.asLiveData(dispatcher)
    }

}

