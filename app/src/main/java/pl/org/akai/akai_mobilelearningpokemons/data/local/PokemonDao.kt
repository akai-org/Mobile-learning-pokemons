package pl.org.akai.akai_mobilelearningpokemons.data.local

import androidx.room.*

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(
        pokemons: List<PokemonEntity>
    )

    @Query("DELETE FROM pokemonentity")
    suspend fun clearPokemos()

    @Query(
        """
            SELECT *
            FROM pokemonentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%'
        """
    )
    suspend fun searchPokemons(query: String): List<PokemonEntity>
}
