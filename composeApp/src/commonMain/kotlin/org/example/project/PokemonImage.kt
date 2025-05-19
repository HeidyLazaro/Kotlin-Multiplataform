package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineDispatcher

expect val ioDispatcher: CoroutineDispatcher

@Composable
expect fun PokemonImage(url: String, modifier: Modifier)
