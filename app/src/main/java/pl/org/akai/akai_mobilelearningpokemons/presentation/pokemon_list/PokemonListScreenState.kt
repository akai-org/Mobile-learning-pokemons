package pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list

import androidx.compose.runtime.mutableStateListOf
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon

data class PokemonListScreenState(
    var isRefreshing: Boolean = false,
    var textFieldValue: String = "",
    val pokemonList: List<Pokemon> = mutableStateListOf<Pokemon>()
)