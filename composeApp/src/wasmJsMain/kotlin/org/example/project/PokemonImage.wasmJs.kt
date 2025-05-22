// PokemonImage.wasmJs.kt
package org.example.project

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.Default

@Composable
actual fun PokemonImage(url: String, modifier: Modifier) {
    val painterResource = asyncPainterResource(url)
    KamelImage(
        resource = painterResource,
        contentDescription = "Imagen de Pokémon",
        modifier = modifier
    )
}
