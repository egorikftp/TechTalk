package com.egoriku.blurhash.screen.encodedecode

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.compose.rememberImagePainter
import coil.size.OriginalSize
import com.egoriku.blurhash.blurhash.rememberBlurHash
import com.egoriku.blurhash.ext.HSpacer

class EncodeDecodeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<UriScreenModel>()

        val blurHashResult by screenModel.hash.collectAsState()

        val getImageContract = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent(),
            onResult = screenModel::processImageResult
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .verticalScroll(state = rememberScrollState())
        ) {
            Button(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = { getImageContract.launch("image/*") }) {
                Text(text = "Get image")
            }
            HSpacer(height = 32.dp)

            val uri by screenModel.imageUri.collectAsState()

            if (uri != Uri.EMPTY) {
                EncoderDecoder(uri = uri, hashResult = blurHashResult)

                if (blurHashResult.blurHash.isEmpty()) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(vertical = 16.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }

    @Composable
    fun EncoderDecoder(uri: Uri, hashResult: HashResult) {
        val painter = rememberImagePainter(
            data = uri,
            builder = {
                size(OriginalSize)
                crossfade(true)
            }
        )

        Image(
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )

        if (hashResult.blurHash.isNotEmpty()) {
            HSpacer(height = 32.dp)

            Text(text = "Hash: ${hashResult.blurHash}", fontWeight = FontWeight.Bold)
            Text(text = "Ratio: ${hashResult.ratio}", fontWeight = FontWeight.Bold)
            HSpacer(height = 16.dp)

            val rememberBlurHash by rememberBlurHash(
                blurHash = hashResult.blurHash,
                ratio = hashResult.ratio
            )

            Image(
                painter = BitmapPainter(rememberBlurHash!!.bitmap.asImageBitmap()),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(hashResult.ratio.toFloat())
            )
        }
    }
}