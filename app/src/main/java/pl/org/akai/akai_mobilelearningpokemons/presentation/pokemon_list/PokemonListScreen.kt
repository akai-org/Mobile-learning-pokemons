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
import kotlinx.coroutines.launch
import pl.org.akai.akai_mobilelearningpokemons.data.local.getPokemonDatabase
import pl.org.akai.akai_mobilelearningpokemons.data.remote.getPokemonApi
import pl.org.akai.akai_mobilelearningpokemons.data.repository.PokemonRepositoryImpl

@Composable
fun PokemonListScreen(
    viewModel: PokemonListScreenViewModel
) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current

    val state = viewModel.state
    val swipeRefreshState = rememberSwipeRefreshState(
        isRefreshing = state.isRefreshing
    )

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedTextField(
            value = state.textFieldValue,
            onValueChange = { value ->
                viewModel.onEvent(PokemonListScreenEvent.OnTextFieldValueChange(value))
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
            onRefresh = { viewModel.onEvent(PokemonListScreenEvent.OnSwipeRefreshed) }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(state.pokemonList.size) { i ->
                    val pokemon = state.pokemonList[i]
                    PokemonListItem(
                        pokemon = pokemon,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                viewModel.onEvent(PokemonListScreenEvent.OnPokemonClick)
                                coroutineScope.launch {
                                    Toast.makeText(context,"Clicked Pokemon //todo add pokemon details screen", Toast.LENGTH_LONG)
                                }
                            }
                            .padding(16.dp)
                    )
                    if(i < state.pokemonList.size) {
                        Divider(modifier = Modifier.padding(
                            horizontal = 16.dp
                        ))
                    }
                }
            }
        }
    }
}