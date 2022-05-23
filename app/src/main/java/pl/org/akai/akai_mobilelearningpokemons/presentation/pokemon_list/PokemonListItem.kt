package pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon

@Composable
fun PokemonListItem(
    pokemon: Pokemon,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(pokemon.imageUrl)
                .crossfade(true)
                .build(),
            contentDescription = "pokemon_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(CircleShape)
                .weight(0.66f)
        )
        Spacer(modifier = Modifier.width(24.dp))

        Column(
            modifier = Modifier
                .weight(1f)
        ){
            Text(text = pokemon.name, style = MaterialTheme.typography.h5)
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "XP: ${pokemon.baseExperience}", style = MaterialTheme.typography.body1)
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Weight: ${pokemon.weight}", style = MaterialTheme.typography.body1)
            }
        }
    }

}