package pl.org.akai.akai_mobilelearningpokemons.domain.repository

import kotlinx.coroutines.flow.Flow
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon
import pl.org.akai.akai_mobilelearningpokemons.utils.Resource

interface PokemonRepository {

    suspend fun getPokemons(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<Pokemon>>>
}