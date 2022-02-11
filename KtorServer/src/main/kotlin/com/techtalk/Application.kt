package com.techtalk

import com.techtalk.plugins.configureRouting
import com.techtalk.util.port
import io.ktor.server.engine.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = port) {
        configureRouting()
    }.start(wait = true)
}
