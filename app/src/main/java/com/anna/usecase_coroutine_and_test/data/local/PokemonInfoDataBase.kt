package com.anna.usecase_coroutine_and_test.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PokemonInfoEntity::class], version = 1)

abstract class PokemonInfoDataBase : RoomDatabase() {

    abstract fun pokemonInfoDao(): PokemonInfoDao

    companion object {
        var INSTANCE: PokemonInfoDataBase? = null

        fun getInstance(context: Context): PokemonInfoDataBase {
            return INSTANCE ?: synchronized(PokemonInfoDataBase::class.java) {
                val instance = builderDataBase(context = context)
                INSTANCE = instance
                instance
            }
        }

        private fun builderDataBase(context: Context) = Room.databaseBuilder(
            context = context.applicationContext,
            klass = PokemonInfoDataBase::class.java,
            name = "pokemonInfoTable.db"
        ).build()
    }

}