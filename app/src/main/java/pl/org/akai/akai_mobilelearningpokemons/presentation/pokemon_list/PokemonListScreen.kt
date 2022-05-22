package pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import pl.org.akai.akai_mobilelearningpokemons.domain.model.Pokemon
import kotlin.coroutines.CoroutineContext

@Composable
fun PokemonListScreen() {
    var isRefreshing by remember {
        mutableStateOf( false )
    }
    var textFieldValue by remember {
        mutableStateOf( "" )
    }
    var pokemonList by remember {
        mutableStateOf( mutableStateListOf<Pokemon>(
            Pokemon(
                name = "Pikachu",
                description = "a yellow one",
                imageUrl = "https://m.media-amazon.com/images/I/61VF0lHZRTL._AC_SX679_.jpg",
                baseExperience = 64,
                weight = 120,
                abilities = emptyList()
            ),
            Pokemon(
                name = "Pikachu",
                description = "a yellow one",
                imageUrl = "https://m.media-amazon.com/images/I/61VF0lHZRTL._AC_SX679_.jpg",
                baseExperience = 64,
                weight = 120,
                abilities = emptyList()
            ),
            Pokemon(
                name = "Pikachu",
                description = "a yellow one",
                imageUrl = "https://m.media-amazon.com/images/I/61VF0lHZRTL._AC_SX679_.jpg",
                baseExperience = 64,
                weight = 120,
                abilities = emptyList()
            ),
            Pokemon(
                name = "Pikachu",
                description = "a yellow one",
                imageUrl = "https://m.media-amazon.com/images/I/61VF0lHZRTL._AC_SX679_.jpg",
                baseExperience = 64,
                weight = 120,
                abilities = emptyList()
            ),Pokemon(
                name = "Pikachu",
                description = "a yellow one",
                imageUrl = "https://m.media-amazon.com/images/I/61VF0lHZRTL._AC_SX679_.jpg",
                baseExperience = 64,
                weight = 120,
                abilities = emptyList()
            )

        ) )
    }

    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = isRefreshing
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = textFieldValue,
            onValueChange = { value ->
                textFieldValue = value
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = "Search...")
            },
            maxLines = 1,
            singleLine = true
        )

        SwipeRefresh(
            state = swipeRefreshState,
            onRefresh = { }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(pokemonList.size) { i ->
                    val pokemon = pokemonList[i]
                    PokemonListItem(
                        pokemon = pokemon,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                coroutineScope.launch {
                                    Toast.makeText(context,"Clicked Pokemon //todo add pokemon details screen", Toast.LENGTH_LONG)
                                }
                            }
                            .padding(16.dp)
                    )
                    if(i < pokemonList.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
    }
}