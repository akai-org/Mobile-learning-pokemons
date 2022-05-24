package pl.org.akai.akai_mobilelearningpokemons.data.remote

import okhttp3.ResponseBody
import pl.org.akai.akai_mobilelearningpokemons.data.remote.dto.PokemonDto
import pl.org.akai.akai_mobilelearningpokemons.data.remote.dto.PokemonsListDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("/api/v2/pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int = 0,
        @Query("limit") limit: Int = 20
    ): PokemonsListDto

    @GET("/api/v2/pokemon/{index}")
    suspend fun getPokemonByIndex(
        @Path("index") index: Int
    ): PokemonDto

    companion object {
        const val BASE_URL = "https://pokeapi.co"
    }
}