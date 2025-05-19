package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import cliente.createHttpClient
import org.example.project.data.PokeApiService
import org.example.project.model.Pokemon
import org.example.project.model.PokemonDetails
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
//import org.jetbrains.skiko.currentOs
//import org.jetbrains.skiko.Os

@Composable
fun App() {
    MaterialTheme {
        PokemonScreen()
    }
}

@Composable
fun PokemonScreen() {
    val client = remember { createHttpClient() }
    val service = remember { PokeApiService(client) }

    var pokemonList by remember { mutableStateOf<List<PokemonDetails>>(emptyList()) }

    LaunchedEffect(Unit) {
        pokemonList = service.getPokemonList(limit = 20) // sin límite visible
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(pokemonList) { pokemon ->
            PokemonCard(pokemon)
        }
    }
}

@Composable
fun PokemonCard(pokemon: PokemonDetails) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Solo en Android/Desktop
            PokemonImage(url = pokemon.sprites.frontDefault ?: "", modifier = Modifier.size(96.dp))

            Spacer(Modifier.width(16.dp))

            Column {
                Text(
                    text = pokemon.name.replaceFirstChar { it.uppercase() },
                    style = MaterialTheme.typography.titleLarge
                )

                Text("Tipos: ${pokemon.types.joinToString(", ") { it.type.name }}")
                Text("Altura: ${pokemon.height / 10.0} m")
                Text("Peso: ${pokemon.weight / 10.0} kg")
                Text("Habilidades: ${pokemon.abilities.joinToString(", ") { it.ability.name }}")
            }
        }
    }
}
