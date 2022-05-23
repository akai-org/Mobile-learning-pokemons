package pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list

sealed class PokemonListScreenEvent {
    class OnTextFieldValueChange(val value: String): PokemonListScreenEvent()
    object OnPokemonClick: PokemonListScreenEvent()
    object OnSwipeRefreshed: PokemonListScreenEvent()
}
