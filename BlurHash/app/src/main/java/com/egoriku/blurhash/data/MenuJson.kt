package com.egoriku.blurhash.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class VenueItemJson(
    val image: ImageJson,
    val overlay: String? = null,
    val venue: VenueJson
)

@Serializable
internal class DeliveryJson(
    val items: List<VenueItemJson>
)

@Serializable
internal class VenueJson(
    val name: String,

    @SerialName("price_range")
    val priceRange: Int,

    @SerialName("short_description")
    val shortDescription: String? = null
)

@Serializable
internal class ImageJson(
    val blurhash: String? = null,
    val url: String
)

