package pl.org.akai.akai_mobilelearningpokemons.domain.model


data class Pokemon(
    val name: String,
    val imageUrl: String,
    val baseExperience: Int,
    val weight: Int,
) {
}