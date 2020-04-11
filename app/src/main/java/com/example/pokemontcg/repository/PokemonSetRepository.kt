package com.example.pokemontcg.repository

import com.example.pokemontcg.datastore.set.PokemonSetDataStore
import com.example.pokemontcg.model.PokemonSet

class PokemonSetRepository private constructor() {

    private var setLocalDataStore: PokemonSetDataStore? = null
    private var setRemoteDataStore: PokemonSetDataStore? = null

    fun init(
        setLocalDataStore: PokemonSetDataStore,
        setRemoteDataStore: PokemonSetDataStore
    ) {
        this.setLocalDataStore = setLocalDataStore
        this.setRemoteDataStore = setRemoteDataStore
    }

    suspend fun getSets(): MutableList<PokemonSet>? {
        val cache = setLocalDataStore?.getSets()
        if (cache != null) return cache
        val response = setRemoteDataStore?.getSets()
        setLocalDataStore?.addAll(response)
        return response
    }

    companion object {
        val instance by lazy { PokemonSetRepository() }
    }
}