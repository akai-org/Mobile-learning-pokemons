package pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import pl.org.akai.akai_mobilelearningpokemons.domain.repository.PokemonRepository
import pl.org.akai.akai_mobilelearningpokemons.utils.Resource

class PokemonListScreenViewModel(
    private val repository: PokemonRepository
) : ViewModel() {

    var state by mutableStateOf(PokemonListScreenState())
        private set

    init {
        getPokemons()
    }

    fun onEvent(event: PokemonListScreenEvent) {
        when(event) {
            PokemonListScreenEvent.OnPokemonClick -> { }
            PokemonListScreenEvent.OnSwipeRefreshed -> { getPokemons(fetchFromRemote = true) }
            is PokemonListScreenEvent.OnTextFieldValueChange -> {
                state = state.copy(textFieldValue = event.value)
                getPokemons(query = state.textFieldValue)
            }
        }
    }

    private fun getPokemons(
        query: String = state.textFieldValue.lowercase(),
        fetchFromRemote: Boolean = false
    ) {
        viewModelScope.launch {
            repository.getPokemons(fetchFromRemote, query)
                .collect { result ->
                when(result) {
                    is Resource.Error -> Unit
                    is Resource.Loading -> { state = state.copy(isRefreshing = result.isLoading) }
                    is Resource.Success -> {
                        result.data?.let { pokemons ->
                            state = state.copy( pokemonList = pokemons, isRefreshing = false)
                        }
                    }
                }

            }
        }
    }
}