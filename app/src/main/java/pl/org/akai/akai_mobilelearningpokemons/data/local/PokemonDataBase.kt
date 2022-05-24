package pl.org.akai.akai_mobilelearningpokemons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [PokemonEntity::class],
    version = 1
)
abstract class PokemonDataBase: RoomDatabase() {
    abstract val dao: PokemonDao
}