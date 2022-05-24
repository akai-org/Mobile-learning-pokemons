package pl.org.akai.akai_mobilelearningpokemons.data.remote.dto

data class PokemonsListDto(
    val count: Int,
    val next: String,
    val previous: Any,
    val results: List<PokemonResult>
)