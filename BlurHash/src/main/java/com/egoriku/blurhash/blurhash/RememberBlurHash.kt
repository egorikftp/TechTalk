package com.egoriku.blurhash.blurhash

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

@Composable
fun rememberBlurHash(
    blurHash: String?,
    context: Context = LocalContext.current
) = remember(blurHash) {
    mutableStateOf(
        BlurhashDecoder.createPlaceholderRatio2F(
            context,
            blurHash
        )
    )
}

@Composable
fun rememberBlurHash(
    blurHash: String?,
    ratio: Double,
    context: Context = LocalContext.current,
) = remember(blurHash) {
    mutableStateOf(
        BlurhashDecoder.createPlaceholder(
            context = context,
            blurHash = blurHash,
            ratio = ratio
        )
    )
}