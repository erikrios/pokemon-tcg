package com.example.pokemontcg.repository

import com.example.pokemontcg.datastore.set.PokemonSetDataStore
import com.example.pokemontcg.model.PokemonSet

class PokemonSetRepository(
    private val setLocalDataStore: PokemonSetDataStore,
    private val setRemoteDataStore: PokemonSetDataStore
) {
    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = setLocalDataStore.getSets()
        if (cache != null) return cache
        val response = setRemoteDataStore.getSets()
        setLocalDataStore.addAll(response)
        return response
    }
}