package com.anna.usecase_coroutine_and_test.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PokemonInfoDao {

    @Insert
    suspend fun insert(pokemonInfo: PokemonInfoEntity)

    @Query("SELECT * FROM pokemonInfoTable")
    suspend fun getPokemonInfo(): PokemonInfoEntity

    @Query("DELETE FROM pokemonInfoTable")
    suspend fun clear()

}