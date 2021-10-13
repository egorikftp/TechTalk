package com.techtalk.util

private const val debug = false

val port: Int
    get() = when {
        debug -> 8080
        else -> System.getenv("PORT").toInt()
    }