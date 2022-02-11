package com.egoriku.blurhash.screen.encodedecode

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.util.Log
import androidx.core.graphics.drawable.toBitmap
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.coroutineScope
import coil.ImageLoader
import coil.request.ImageRequest
import coil.target.Target
import com.egoriku.blurhash.blurhash.encoder.BlurHashEncoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

data class HashResult(
    val ratio: Double = 0.0,
    val blurHash: String = ""
)

class UriScreenModel(private val context: Context) : ScreenModel {

    private val stateFlow = MutableStateFlow<Uri>(Uri.EMPTY)
    val imageUri = stateFlow.asStateFlow()

    val hash = MutableStateFlow(HashResult())

    fun processImageResult(uri: Uri?) {
        if (uri != null) {
            stateFlow.value = uri
            hash.value = HashResult()

            extractBlurHash()
        }
    }

    private fun extractBlurHash() {
        val value = imageUri.value

        val request = ImageRequest.Builder(context)
            .data(value)
            .allowHardware(false)
            .target(DrawableTarget {
                coroutineScope.launch {
                    withContext(Dispatchers.IO) {
                        val bitmap = it.toBitmap()
                        val width = bitmap.width
                        val height = bitmap.height

                        val ratio = (width.toDouble() / height.toDouble())
                        Log.d("kek", "ratio = $ratio")

                        hash.value = HashResult(
                            ratio = ratio,
                            blurHash = BlurHashEncoder.encode(bitmap)
                        )
                    }
                }
            })
            .build()

        ImageLoader.invoke(context).enqueue(request)
    }

    class DrawableTarget(private val onSuccessCallback: (Drawable) -> Unit) : Target {

        override fun onSuccess(result: Drawable) {
            onSuccessCallback(result)
        }
    }
}