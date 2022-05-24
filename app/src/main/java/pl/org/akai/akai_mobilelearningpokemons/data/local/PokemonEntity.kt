package pl.org.akai.akai_mobilelearningpokemons.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PokemonEntity(
    val name: String,
    val imageUrl: String,
    val baseExperience: Int,
    val weight: Int,
    @PrimaryKey val id: Int? = null
)

