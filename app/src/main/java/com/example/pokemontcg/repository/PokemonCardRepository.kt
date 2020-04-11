package com.example.pokemontcg.repository

import com.example.pokemontcg.datastore.pokemon.PokemonCardDataStore
import com.example.pokemontcg.model.PokemonCard

class PokemonCardRepository private constructor() {
    private var pokemonCardLocalDataStore: PokemonCardDataStore? = null
    private var pokemonCardRemoteDataStore: PokemonCardDataStore? = null

    fun init(
        pokemonCardLocalDataStore: PokemonCardDataStore,
        pokemonCardRemoteDataStore: PokemonCardDataStore
    ) {
        this.pokemonCardLocalDataStore = pokemonCardLocalDataStore
        this.pokemonCardRemoteDataStore = pokemonCardRemoteDataStore
    }

    suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val cache = pokemonCardLocalDataStore?.getPokemons(set)
        if (cache != null) return cache
        val response = pokemonCardRemoteDataStore?.getPokemons(set)
        pokemonCardLocalDataStore?.addAll(set, response)
        return response
    }

    companion object {
        val instance by lazy { PokemonCardRepository() }
    }
}