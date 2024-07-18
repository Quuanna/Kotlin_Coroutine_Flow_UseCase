package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7


import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.core.api.PokemonCline
import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoDataBase
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonLocalDataSource
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonRemoteDataSource
import com.anna.usecase_coroutine_and_test.data.repo.offlineFirst.OfflineFirstPokemonRepository
import com.anna.usecase_coroutine_and_test.data.repo.offlineFirst.OfflineFirstPokemonRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CoroutineUseCase7ViewModel(
    val repository: OfflineFirstPokemonRepository
) : ViewModel() {

    val uiState: LiveData<UiState> get() = _uiState
    private val _uiState: MutableLiveData<UiState> = MutableLiveData()

    val pokemonInfo: LiveData<PokemonInfo> get() = _pokemonInfo
    private val _pokemonInfo: MutableLiveData<PokemonInfo> = MutableLiveData()

    companion object {
        fun factory(context: Context) = viewModelFactory {
            initializer {
                CoroutineUseCase7ViewModel(
                    repository = OfflineFirstPokemonRepositoryImpl(
                        localDataSource = PokemonLocalDataSource(
                            dataBase = PokemonInfoDataBase.getInstance(
                                context.applicationContext
                            ).pokemonInfoDao(),
                            ioDispatcher = Dispatchers.IO
                        ),
                        remoteDataSource = PokemonRemoteDataSource(
                            apiService = PokemonCline.apiService,
                            ioDispatcher = Dispatchers.IO
                        )
                    )
                )
            }
        }
    }

    /**
     * 預設先取DataBase，在call NetWork
     */
    fun performFetchData(page: Int = 1) {
        getLocalBaseData { data ->
            if (data != null) {
                _pokemonInfo.value = PokemonInfo(data.name, data.imageUrl)
                _uiState.value = UiState.Success(DataSource.DATABASE, "loading Data form DataBase")
                _uiState.value = UiState.Error(DataSource.NETWORK, "loading Data form Network")
            } else {
                _uiState.value = UiState.Error(DataSource.DATABASE, "Database empty!")

                // dataBase empty
                getNetworkRequest(page) { response ->
                    if (response != null) {
                        _pokemonInfo.value = PokemonInfo(response.name, response.imageUrl)
                        _uiState.value = UiState.Success(DataSource.NETWORK, "loading Data form Network")
                    } else {
                        _uiState.value = UiState.Error(DataSource.NETWORK, "Something went wrong!")
                    }
                }
            }
        }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            repository.localDatabaseClear()
        }
    }

    private fun getLocalBaseData(callback: (PokemonInfo?) -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading.LoadFromDB
            val localData = repository.localDataPokemonInfo()
            if (localData != null && localData.name.isNotEmpty() && localData.imageUrl.isNotEmpty()) {
                callback.invoke(PokemonInfo(localData.name, localData.imageUrl))
            } else {
                callback.invoke(null)
            }
        }
    }

    private fun getNetworkRequest(page: Int, callback: (PokemonInfo?) -> Unit) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading.LoadFromNetWork
            val remoteData = repository.networkRequestFetch(page)
            remoteData?.let {
                callback.invoke(PokemonInfo(it.name, it.imageUrl))
            } ?: callback.invoke(null)
        }
    }
}

enum class DataSource {
    DATABASE,
    NETWORK
}