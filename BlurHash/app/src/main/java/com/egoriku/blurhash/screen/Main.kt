package com.egoriku.blurhash.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.egoriku.blurhash.screen.sample.BlurHashPlaceHolder
import com.egoriku.blurhash.screen.sample.ColorPlaceHolder
import com.egoriku.blurhash.screen.sample.EncodeDecode
import com.egoriku.blurhash.screen.sample.NoPlaceHolder

class Main : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                Button(onClick = { navigator.push(NoPlaceHolder()) }) {
                    Text(text = "No placeholder")
                }

                Button(onClick = { navigator.push(ColorPlaceHolder()) }) {
                    Text(text = "Color placeholder")
                }

                Button(onClick = { navigator.push(BlurHashPlaceHolder()) }) {
                    Text(text = "BlurHash placeholder")
                }

                Button(onClick = { navigator.push(EncodeDecode()) }) {
                    Text(text = "Encode/Decode")
                }
            }
        }
    }
}