package com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase10

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.anna.usecase_coroutine_and_test.constant.DataSource
import com.anna.usecase_coroutine_and_test.core.api.PokemonCline
import com.anna.usecase_coroutine_and_test.data.local.PokemonInfoDataBase
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonLocalRemoteDataSourceImpl
import com.anna.usecase_coroutine_and_test.data.repo.dataSource.PokemonRemoteDataSourceImpl
import com.anna.usecase_coroutine_and_test.data.repo.offlineFirst.OfflineFirstPokemonAsyncRepositoryImpl
import com.anna.usecase_coroutine_and_test.data.repo.offlineFirst.OfflineFirstPokemonRepository
import com.anna.usecase_coroutine_and_test.usecase.coroutine.usecase7.UiState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CoroutineUseCase10ViewModel(private val repository: OfflineFirstPokemonRepository) : ViewModel() {

    val uiState: LiveData<UiState> get() = _uiState
    private val _uiState: MutableLiveData<UiState> = MutableLiveData()

    val pokemonInfo: LiveData<PokemonInfo> get() = _pokemonInfo
    private val _pokemonInfo: MutableLiveData<PokemonInfo> = MutableLiveData()


    companion object {
        fun factory(context: Context) = viewModelFactory {
            initializer {
                CoroutineUseCase10ViewModel(
                    OfflineFirstPokemonAsyncRepositoryImpl(
                        scope = CoroutineScope(Dispatchers.IO),
                        remoteDataSource = PokemonRemoteDataSourceImpl(apiService = PokemonCline.apiService),
                        localDataSource = PokemonLocalRemoteDataSourceImpl(
                            dataBase = PokemonInfoDataBase.getInstance(context.applicationContext)
                                .pokemonInfoDao()
                        ),
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
                _uiState.value = UiState.Success(DataSource.DATABASE, DataSource.DATABASE.successMsg)
                _uiState.value = UiState.Error(DataSource.NETWORK, DataSource.NETWORK.successMsg)
            } else {
                _uiState.value = UiState.Error(DataSource.DATABASE, DataSource.DATABASE.errorMsg)

                // dataBase empty
                getNetworkRequest(page) { response ->
                    if (response != null) {
                        _pokemonInfo.value = PokemonInfo(response.name, response.imageUrl)
                        _uiState.value =
                            UiState.Success(DataSource.NETWORK, DataSource.NETWORK.successMsg)
                    } else {
                        _uiState.value = UiState.Error(DataSource.NETWORK, DataSource.DATABASE.errorMsg)
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
