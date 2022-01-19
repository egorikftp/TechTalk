package com.egoriku.blurhash.screen.sample

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.compose.rememberImagePainter
import com.egoriku.blurhash.data.MenuItem
import com.egoriku.blurhash.ext.HSpacer
import com.egoriku.blurhash.screen.DataScreenModel
import com.egoriku.blurhash.screen.DeliveryFeed

class NoPlaceHolder : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DataScreenModel>()

        val items by screenModel.delivery.collectAsState()

        if (items.isNotEmpty()) {
            DeliveryFeed {
                items(items) {
                    NoPlaceholderCard(data = it)
                }
            }
        }
    }

    @Composable
    private fun NoPlaceholderCard(data: MenuItem) {
        Card(
            shape = RoundedCornerShape(10.dp),
            elevation = 10.dp,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = rememberImagePainter(
                        data = data.image,
                        builder = {
                            crossfade(true)
                        },
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(2.0f)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    HSpacer(16.dp)
                    Text(text = data.name, fontWeight = FontWeight.Bold)
                    Text(text = data.desc)
                    HSpacer(16.dp)
                }

                Divider(Modifier.fillMaxWidth())

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    HSpacer(8.dp)
                    Text(text = "${data.priceRange} • ", color = Color.Gray)
                    HSpacer(8.dp)
                }
            }
        }
    }
}