package com.example.pokemontcg.setlist

import com.example.pokemontcg.model.PokemonSet

data class SetListViewState(
    var loading: Boolean = false,
    var error: Exception? = null,
    var data: MutableList<PokemonSet>? = null
)