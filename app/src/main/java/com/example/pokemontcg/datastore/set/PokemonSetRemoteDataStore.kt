package com.example.pokemontcg.datastore.set

import com.example.pokemontcg.model.PokemonSet
import com.example.pokemontcg.webservice.PokemonTcgService

class PokemonSetRemoteDataStore(private val pokemonTcgService: PokemonTcgService) :
    PokemonSetDataStore {

    override suspend fun getSets(): MutableList<PokemonSet>? {
        val response = pokemonTcgService.getSets()
        if (response.isSuccessful) return response.body()?.sets

        throw Exception("A problem happened when request data, status error ${response.code()}")
    }

    override suspend fun addAll(sets: MutableList<PokemonSet>?) {
    }
}