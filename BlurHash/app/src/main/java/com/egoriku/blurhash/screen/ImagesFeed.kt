package com.egoriku.blurhash.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.LocalImageLoader

@Composable
fun ImagesFeed(content: LazyListScope.() -> Unit) {
    Column {
        Text(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            text = "Images for me",
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content
        )

        val imageLoader = LocalImageLoader.current
        DisposableEffect(key1 = Unit) {
            onDispose {
                imageLoader.memoryCache.clear()
            }
        }
    }
}
