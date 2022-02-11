package com.egoriku.blurhash.data

import android.content.Context
import com.egoriku.blurhash.ext.readJsonFromAsset
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json

class UnsplashRepository(private val context: Context) {

    fun provideDeliveryData() = convertJsonToDomain(getDeliveryJsonModel())

    private fun convertJsonToDomain(json: List<ImageJson>): List<UnsplashItem> {
        return json.map(::convertVenueJsonToVenueDomain)
    }

    private fun convertVenueJsonToVenueDomain(src: ImageJson): UnsplashItem {
        return UnsplashItem(
            image = src.urls.raw,
            blurHash = src.blurhash,
            name = src.user.name,
            desc = "Blurhash: ${src.blurhash}"
        )
    }

    private fun getDeliveryJsonModel(): List<ImageJson> {
        val format = Json { ignoreUnknownKeys = true }
        val json = context.readJsonFromAsset("images.json")
        return format.decodeFromString(ListSerializer(ImageJson.serializer()), json)
    }
}
