// PokemonImage.wasmJs.kt
package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.material3.Text
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Default

@Composable
actual fun PokemonImage(url: String, modifier: Modifier) {
    Text("Imagen no disponible en esta plataforma", modifier = modifier)
}
