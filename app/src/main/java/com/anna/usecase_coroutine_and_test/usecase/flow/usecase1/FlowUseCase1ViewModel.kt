package com.anna.usecase_coroutine_and_test.usecase.flow.usecase1

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.mock.api.MockNetworkInterceptor
import com.anna.usecase_coroutine_and_test.mock.api.createMockApi
import com.anna.usecase_coroutine_and_test.mock.repo.MockRepository
import com.anna.usecase_coroutine_and_test.mock.repo.MockRepositoryImpl
import com.anna.usecase_coroutine_and_test.usecase.flow.UiSateForFlow
import com.google.gson.Gson
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

class FlowUseCase1ViewModel(private val repository: MockRepository) : ViewModel(){

    companion object {
        val Factory = viewModelFactory {
            initializer {
                FlowUseCase1ViewModel(repository = MockRepositoryImpl(mockApi()))
            }
        }
    }

    val getPokemon: LiveData<UiSateForFlow> = repository.fetchPokemon()
        .map { pokemon ->
            UiSateForFlow.Success(pokemon) as UiSateForFlow
        }.onStart {
            emit(UiSateForFlow.Loading)
        }.catch {
            emit(UiSateForFlow.Error("Network request failed!"))
        }.asLiveData()

}


fun mockApi() = createMockApi(
    MockNetworkInterceptor()
        .mock(
            "http://localhost/pokemon/1",
            {
                Gson().toJson(
                    PokemonInfo(
                        "pokemon",
                        "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/1.png"
                    )
                )
            },
            200,
            1000
        )
        .mock(
            "http://localhost/pokemon/2",
            { "Something went wrong on servers side" },
            500,
            100
        )
)