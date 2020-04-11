package com.example.pokemontcg.webservice

import com.example.pokemontcg.model.PokemonSet
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PokemonTcgService {

    @GET("sets")
    suspend fun getSets(): Response<PokemonSet.PokemonSetResponse>
}