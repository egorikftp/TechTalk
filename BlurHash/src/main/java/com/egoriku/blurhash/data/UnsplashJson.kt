package com.egoriku.blurhash.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class ImageJson(
    @SerialName("blur_hash")
    val blurhash: String? = null,

    val urls: Urls,
    val user: USer
)

@Serializable
internal class Urls(
    val raw: String
)

@Serializable
internal class USer(
    val name: String
)
