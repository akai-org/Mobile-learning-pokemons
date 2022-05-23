package pl.org.akai.akai_mobilelearningpokemons.data.repository

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import pl.org.akai.akai_mobilelearningpokemons.data.local.PokemonDataBase
import pl.org.akai.akai_mobilelearningpokemons.data.mapper.toPokemon
import pl.org.akai.akai_mobilelearningpokemons.data.mapper.toPokemonEntity
import pl.org.akai.akai_mobilelearningpokemons.data.remote.PokemonApi
import pl.org.akai.akai_mobilelearningpokemons.data.remote.dto.PokemonDto
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon
import pl.org.akai.akai_mobilelearningpokemons.domain.repository.PokemonRepository
import pl.org.akai.akai_mobilelearningpokemons.utils.Resource
import retrofit2.HttpException
import java.io.IOException

class PokemonRepositoryImpl(
    private val api: PokemonApi,
    private val db: PokemonDataBase
): PokemonRepository {

    private val dao = db.dao

    override suspend fun getPokemons(
        fetchFromRemote: Boolean,
        query: String,
    ): Flow<Resource<List<Pokemon>>> {
        return flow {
            emit(Resource.Loading(true))

            val localPokemons = dao.searchPokemons(query)
            emit(Resource.Success(data = localPokemons.map { it.toPokemon() }))

            val isDbEmpty = localPokemons.isEmpty() && query.isBlank()
            val shouldLoadFromRemote = fetchFromRemote || isDbEmpty

            if (!shouldLoadFromRemote) {
                emit(Resource.Loading(false))
                return@flow
            }

            val remotePokemons = try {
                val pokemons = mutableListOf<PokemonDto>()
                val responsePokemonList = api.getPokemons()

                responsePokemonList.results.forEach { pokemonResult ->

                    val index =
                        pokemonResult.url.substringAfter("${PokemonApi.BASE_URL}/api/v2/pokemon/").substringBefore("/").toInt()

                    val pokemonDto = api.getPokemonByIndex(index)
                    pokemons.add(pokemonDto)

                }
                pokemons
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }
            emit(Resource.Success(
                data = remotePokemons?.map { it.toPokemon() }
            ))

            remotePokemons?.let { pokemons ->
                dao.clearPokemos()
                dao.insertPokemon(
                    pokemons.map { it.toPokemonEntity() }
                )
                emit(Resource.Success(
                    data = dao
                        .searchPokemons("")
                        .map { it.toPokemon() }
                ))
            }
            emit(Resource.Loading(false))
        }
    }
}