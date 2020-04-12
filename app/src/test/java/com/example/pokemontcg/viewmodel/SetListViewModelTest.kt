package com.example.pokemontcg.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.pokemontcg.repository.PokemonSetRepository
import com.example.pokemontcg.setlist.SetListViewModel
import junit.framework.Assert.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class SetListViewModelTest {
    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    @Mock
    var pokemonSetRepository: PokemonSetRepository? = null

    var setListViewModel: SetListViewModel? = null

    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        setListViewModel = SetListViewModel(pokemonSetRepository!!)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @Test
    fun shouldLoadingWhenFirstInitialized() {
        val state = setListViewModel!!.viewState.value!!
        assertTrue(state.loading)
        assertNull(state.error)
        assertNull(state.data)
    }

    @Test
    fun shouldStopLoadingAndGiveDataWhenSuccess() {
        runBlocking {
            `when`(pokemonSetRepository?.getSets()).thenReturn(mutableListOf())
            setListViewModel?.getSets()
            val state = setListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNull(state.error)
            assertNull(state.data)
        }
    }

    @Test
    fun shouldThrowErroeWhenRepositoryIsThrowingError() {
        runBlocking {
            `when`(pokemonSetRepository?.getSets()).thenAnswer { throw Exception() }
            setListViewModel?.getSets()
            val state = setListViewModel!!.viewState.value!!
            assertFalse(state.loading)
            assertNotNull(state.error)
            assertNull(state.data)
        }
    }
}