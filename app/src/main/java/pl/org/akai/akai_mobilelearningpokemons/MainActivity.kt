package pl.org.akai.akai_mobilelearningpokemons

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pl.org.akai.akai_mobilelearningpokemons.data.local.getPokemonDatabase
import pl.org.akai.akai_mobilelearningpokemons.data.remote.getPokemonApi
import pl.org.akai.akai_mobilelearningpokemons.data.repository.PokemonRepositoryImpl
import pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list.PokemonListScreen
import pl.org.akai.akai_mobilelearningpokemons.presentation.pokemon_list.PokemonListScreenViewModel
import pl.org.akai.akai_mobilelearningpokemons.ui.theme.AKAIMobileLearningPokemonsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = PokemonListScreenViewModel(
            PokemonRepositoryImpl(
                api = getPokemonApi(),
                db = getPokemonDatabase(this )
            )
        )

        setContent {
            AKAIMobileLearningPokemonsTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "list_screen"
                    ) {
                        composable("list_screen") {
                            PokemonListScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}