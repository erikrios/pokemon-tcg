package com.example.pokemontcg

import android.app.Application
import com.example.pokemontcg.datastore.pokemon.PokemonCardLocalDataStore
import com.example.pokemontcg.datastore.pokemon.PokemonCardRemoteDataStore
import com.example.pokemontcg.datastore.set.PokemonSetLocalDataStore
import com.example.pokemontcg.datastore.set.PokemonSetRemoteDataStore
import com.example.pokemontcg.repository.PokemonCardRepository
import com.example.pokemontcg.repository.PokemonSetRepository
import com.example.pokemontcg.webservice.RetrofitApp

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val pokemonTcgService = RetrofitApp.POKEMON_TCG_SERVICE
        PokemonSetRepository.instance.apply {
            init(PokemonSetLocalDataStore(), PokemonSetRemoteDataStore(pokemonTcgService))
        }

        PokemonCardRepository.instance.apply {
            init(PokemonCardLocalDataStore(), PokemonCardRemoteDataStore(pokemonTcgService))
        }
    }
}