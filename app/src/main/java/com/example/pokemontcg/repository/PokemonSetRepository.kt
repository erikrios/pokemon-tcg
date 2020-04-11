package com.example.pokemontcg.repository

import com.example.pokemontcg.datastore.set.PokemonSetDataStore
import com.example.pokemontcg.model.PokemonSet

class PokemonSetRepository private constructor() : BaseRepository<PokemonSetDataStore>() {

    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = localDataStore?.getSets()
        if (cache != null) return cache
        val response = remoteDataStore?.getSets()
        localDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}