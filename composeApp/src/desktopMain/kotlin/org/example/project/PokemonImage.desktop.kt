// PokemonImage.desktop.kt
package org.example.project

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.toComposeImageBitmap
import androidx.compose.ui.unit.dp
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.get
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.skia.Image
//import org.jetbrains.skiko.toComposeImageBitmap

actual val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

@Composable
actual fun PokemonImage(url: String, modifier: Modifier) {
    var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

    LaunchedEffect(url) {
        val byteArray = HttpClient().get(url).body<ByteArray>()
        val skiaImage = Image.makeFromEncoded(byteArray)
        imageBitmap = skiaImage.toComposeImageBitmap()
    }

    imageBitmap?.let {
        Image(
            painter = BitmapPainter(it),
            contentDescription = null,
            modifier = modifier
        )
    }
}
