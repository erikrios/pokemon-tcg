package com.example.pokemontcg.pokemonlist

import com.example.pokemontcg.model.PokemonCard

data class PokemonListViewState(
    val loading: Boolean = false,
    val error: Exception? = null,
    val data: MutableList<PokemonCard>? = null
)