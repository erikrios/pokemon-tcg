package com.example.pokemontcg.datastore.pokemon

import com.example.pokemontcg.model.PokemonCard
import com.example.pokemontcg.webservice.PokemonTcgService

class PokemonCardRemoteDataStore(private val pokemonTcgService: PokemonTcgService) :
    PokemonCardDataStore {

    override suspend fun getPokemons(set: String): MutableList<PokemonCard>? {
        val response = pokemonTcgService.getCards(set)
        if (response.isSuccessful) return response.body()?.cards

        throw Exception("A problem happened when request data, status error ${response.code()}")
    }

    override suspend fun addAll(set: String, pokemons: MutableList<PokemonCard>?) {

    }
}