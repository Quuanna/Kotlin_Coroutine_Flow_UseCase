package com.anna.usecase_coroutine_and_test.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.anna.usecase_coroutine_and_test.data.model.PokemonInfo

@Entity(tableName = "pokemonInfoTable")
data class PokemonInfoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val imageUrl: String
)

fun PokemonInfoEntity.asExternalModel() = PokemonInfo(name, imageUrl)

fun PokemonInfo.asEntity() = PokemonInfoEntity(0, name, imageUrl)