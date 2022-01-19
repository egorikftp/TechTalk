package com.egoriku.blurhash.data

import android.content.Context
import com.egoriku.blurhash.ext.readJsonFromAsset
import kotlinx.serialization.json.Json

class MenuRepository(private val context: Context) {

    fun provideDeliveryData() = convertDeliveryJsonToDeleveryDomain(getDeliveryJsonModel())

    private fun convertDeliveryJsonToDeleveryDomain(json: DeliveryJson): List<MenuItem> {
        return json.items.map(::convertVenueJsonToVenueDomain)
    }

    private fun convertVenueJsonToVenueDomain(src: VenueItemJson): MenuItem {
        val priceRange = "€".repeat(src.venue.priceRange)

        return MenuItem(
            image = src.image.url,
            blurHash = src.image.blurhash,
            overlayText = src.overlay?.takeIf { it.isNotBlank() },
            name = src.venue.name,
            desc = src.venue.shortDescription ?: "No description",
            priceRange = priceRange
        )
    }

    private fun getDeliveryJsonModel(): DeliveryJson {
        val format = Json { ignoreUnknownKeys = true }
        val json = context.readJsonFromAsset("delivery.json")
        return format.decodeFromString(DeliveryJson.serializer(), json)
    }
}
