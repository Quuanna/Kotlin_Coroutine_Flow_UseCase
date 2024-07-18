package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.constant.UiState
import com.anna.usecase_coroutine_and_test.core.api.PokemonCline
import com.anna.usecase_coroutine_and_test.data.network.models.PokemonListResponse
import com.anna.usecase_coroutine_and_test.data.model.PokemonApiResultOrder
import com.anna.usecase_coroutine_and_test.data.repo.PokemonRepository
import com.anna.usecase_coroutine_and_test.data.repo.PokemonRepositoryImpl
import com.anna.usecase_coroutine_and_test.usecase.BaseViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import org.mockito.internal.matchers.Null

class CoroutineUseCase3ViewModel(val repository: PokemonRepository) : BaseViewModel<Null>() {

    val sequentiallyResultOrder: LiveData<List<PokemonApiResultOrder>> get() = _sequentiallyResultOrder
    val concurrentlyResultOrder: LiveData<List<PokemonApiResultOrder>> get() = _concurrentlyResultOrder
    private var _sequentiallyResultOrder = MutableLiveData<List<PokemonApiResultOrder>>()
    private var _concurrentlyResultOrder = MutableLiveData<List<PokemonApiResultOrder>>()

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                CoroutineUseCase3ViewModel(repository = PokemonRepositoryImpl(PokemonCline.apiService))
            }
        }
    }

    fun getPokemonSequentially() {
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val one = repository.fetchPokemonList(1)
            val two = repository.fetchPokemonList(2)
            val three = repository.fetchPokemonList(3)
            val resultList: List<Result<PokemonListResponse>> = listOf(one, two, three)
            val orderList: ArrayList<PokemonApiResultOrder> = arrayListOf()
            resultList.forEachIndexed { index, result ->
                if (result.isSuccess) {
                    try {
                        orderList.add(PokemonApiResultOrder(orderNum = index, responseText= result.getOrThrow().results.first().name))
                    } catch (e: Exception) {
                        uiState.value = UiState.Error(e.message.toString())
                    }
                }
            }

            if (orderList.isNotEmpty()) {
                _sequentiallyResultOrder.value = orderList
                uiState.value = UiState.Success
            } else {
                uiState.value = UiState.Error()
            }
        }
    }

    fun getPokemonConcurrently() {
        val oneDeferred = viewModelScope.async { repository.fetchPokemonList(1) }
        val twoDeferred = viewModelScope.async { repository.fetchPokemonList(2) }
        val threeDeferred = viewModelScope.async { repository.fetchPokemonList(3) }
        val orderList: ArrayList<PokemonApiResultOrder> = arrayListOf()
        uiState.value = UiState.Loading
        viewModelScope.launch {
            val deferredList = awaitAll(oneDeferred, twoDeferred, threeDeferred)
            try {
                deferredList.forEachIndexed { index, result ->
                    if (result.isSuccess) {
                        orderList.add(PokemonApiResultOrder(index, result.getOrThrow().results.first().name))
                    }
                }
            } catch (e: Exception) {
                uiState.value = UiState.Error(e.message.toString())
            }

            if (orderList.isNotEmpty()) {
                _concurrentlyResultOrder.value = orderList
                uiState.value = UiState.Success
            } else {
                uiState.value = UiState.Error()
            }
        }
    }

}