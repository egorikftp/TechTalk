package com.egoriku.blurhash.screen.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import com.egoriku.blurhash.R
import com.egoriku.blurhash.blurhash.encoder.BlurHashEncoder
import com.egoriku.blurhash.blurhash.rememberBlurHash
import com.egoriku.blurhash.data.MenuItem
import com.egoriku.blurhash.ext.HSpacer
import com.egoriku.blurhash.screen.DataScreenModel

class EncodeDecode : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DataScreenModel>()

        val items by screenModel.delivery.collectAsState()

        if (items.isNotEmpty()) {
            EncoderDecoder(items.first())
        }
    }

    @OptIn(ExperimentalCoilApi::class)
    @Composable
    fun EncoderDecoder(first: MenuItem) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            var blurHash by remember { mutableStateOf("") }

            val painter = rememberImagePainter(
                data = first.image,
                builder = {
                    crossfade(true)
                    placeholder(R.color.placeholder)
                    allowHardware(false)
                }
            )

            val state = painter.state
            if (state is ImagePainter.State.Success) {
                val bitmap = state.result.drawable.toBitmap()

                blurHash = BlurHashEncoder.encode(bitmap)
            }

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(2.0f)
            )

            if (blurHash.isNotEmpty()) {
                HSpacer(height = 32.dp)

                Text(text = "BlurHash: $blurHash")
                val rememberBlurHash by rememberBlurHash(blurHash = blurHash)

                Image(
                    painter = BitmapPainter(rememberBlurHash!!.bitmap.asImageBitmap()),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2.0f)
                )
            }
        }
    }
}