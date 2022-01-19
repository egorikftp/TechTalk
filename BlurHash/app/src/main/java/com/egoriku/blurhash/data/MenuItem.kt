package com.egoriku.blurhash.data

data class MenuItem(
    val image: String,
    val blurHash: String?,
    val overlayText: String?,
    val name: String,
    val desc: String,
    val priceRange: String
)
