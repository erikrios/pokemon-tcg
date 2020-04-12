package com.example.pokemontcg.pokemonlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokemontcg.repository.PokemonCardRepository

@Suppress("UNCHECKED_CAST")
class PokemonListViewModelFactory(private val pokemonRepository: PokemonCardRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonListViewModel::class.java))
            return PokemonListViewModel(pokemonRepository) as T
        throw IllegalArgumentException()
    }
}