package pl.org.akai.akai_mobilelearningpokemons.data.mapper

import pl.org.akai.akai_mobilelearningpokemons.data.local.PokemonEntity
import pl.org.akai.akai_mobilelearningpokemons.data.remote.dto.PokemonDto
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon

fun PokemonDto.toPokemon(): Pokemon {
    return Pokemon(
        name = name,
        baseExperience = base_experience,
        weight = weight,
        imageUrl = sprites.front_default
    )
}

fun PokemonDto.toPokemonEntity(): PokemonEntity {
    return PokemonEntity(
        name = name,
        imageUrl = sprites.front_default,
        baseExperience = base_experience,
        weight = weight,
    )
}


fun PokemonEntity.toPokemon() : Pokemon {
    return Pokemon(
        name, imageUrl, baseExperience, weight
    )
}