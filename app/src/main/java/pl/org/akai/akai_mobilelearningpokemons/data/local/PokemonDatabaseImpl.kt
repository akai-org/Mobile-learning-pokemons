package pl.org.akai.akai_mobilelearningpokemons.data.local

import android.content.Context
import androidx.room.Room

fun getPokemonDatabase(context: Context): PokemonDataBase {
    return Room.databaseBuilder(
        context,
        PokemonDataBase::class.java,
        "pokemons.db"
    ).build()
}