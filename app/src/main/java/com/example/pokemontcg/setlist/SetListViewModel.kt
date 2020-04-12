package com.example.pokemontcg.setlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemontcg.repository.PokemonSetRepository
import kotlinx.coroutines.launch

class SetListViewModel(private val pokemonSets: PokemonSetRepository) : ViewModel() {
    private val mViewState = MutableLiveData<SetListViewState>().apply {
        value = SetListViewState(loading = true)
    }

    val viewState: LiveData<SetListViewState>
        get() = mViewState

    init {
        getSets()
    }

    fun getSets() = viewModelScope.launch {
        try {
            val data = pokemonSets.getSets()
            mViewState.value = mViewState.value?.copy(loading = true, error = null, data = data)
        } catch (ex: Exception) {
            mViewState.value = mViewState.value?.copy(loading = false, error = ex, data = null)
        }
    }
}