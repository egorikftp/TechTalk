package com.techtalk.entity

import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Int,
    var title: String,
    var isDone: Boolean
)