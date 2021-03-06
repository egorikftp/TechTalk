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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.egoriku.blurhash.R
import com.egoriku.blurhash.data.UnsplashItem
import com.egoriku.blurhash.ext.HSpacer
import com.egoriku.blurhash.screen.DataScreenModel
import com.egoriku.blurhash.screen.ImagesFeed

class ColorPlaceHolder : Screen {

    @Composable
    override fun Content() {
        val screenModel = getScreenModel<DataScreenModel>()

        val items by screenModel.unsplashState.collectAsState()

        if (items.isNotEmpty()) {
            ImagesFeed {
                items(items) {
                    ColorPlaceholderCard(data = it)
                }
            }
        }
    }

    @Composable
    private fun ColorPlaceholderCard(data: UnsplashItem) {
        val context = LocalContext.current
        val colorPlaceHolder = ContextCompat.getDrawable(context, R.color.placeholder)

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
                            placeholder(colorPlaceHolder)
                            scale(Scale.FILL)
                        },
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1.8f)
                )
                Column(modifier = Modifier.padding(start = 16.dp)) {
                    HSpacer(16.dp)
                    Text(text = data.name, fontWeight = FontWeight.Bold)
                    HSpacer(16.dp)
                }

                Divider(Modifier.fillMaxWidth())

                Column(modifier = Modifier.padding(start = 16.dp)) {
                    HSpacer(8.dp)
                    Text(text = data.desc, color = Color.Gray)
                    HSpacer(8.dp)
                }
            }
        }
    }
}