package com.example.pokemontcg.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemontcg.pokemonlist.PokemonListViewModel
import com.example.pokemontcg.repository.PokemonCardRepository
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class PokemonListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var pokemonRepository: PokemonCardRepository? = null

    var pokemonListViewModel: PokemonListViewModel? = null

    @Before
    fun unit() {
        MockitoAnnotations.initMocks(this)
        pokemonListViewModel = PokemonListViewModel(pokemonRepository!!)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = pokemonListViewModel!!.viewState.value!!
        assertTrue(state.loading)
        assertNull(state.error)
        assertNull(state.data)
    }

    @Test
    fun shouldStopLoadingAndGivenDataWhenSuccess() {
        runBlocking {
            `when`(pokemonRepository?.getPokemons(anyString())).thenReturn(mutableListOf())
            pokemonListViewModel?.getPokemons(anyString())
            val state = pokemonListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNull(state.error)
            assertNotNull(state.data)
        }
    }

    @Test
    fun shouldThrowErrorWhenRepositoryIsThrowingError() {
        runBlocking {
            `when`(pokemonRepository?.getPokemons(anyString())).thenAnswer { throw Exception() }
            pokemonListViewModel?.getPokemons(anyString())
            val state = pokemonListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertNull(state.data)
        }
    }
}