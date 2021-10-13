package com.techtalk.entity

import kotlinx.serialization.Serializable

@Serializable
data class TodoDraft(
    val title: String,
    val isDone: Boolean
)
