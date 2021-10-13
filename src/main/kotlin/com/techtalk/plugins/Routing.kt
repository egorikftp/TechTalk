package com.techtalk.plugins

import com.techtalk.entity.Todo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.serialization.*
import kotlinx.serialization.json.Json

fun Application.configureRouting() {
    install(CallLogging)
    install(ContentNegotiation) {
        json(
            Json {
                ignoreUnknownKeys = true
            }
        )
    }

    routing {

        val todos = listOf(
            Todo(id = 1, title = "Prepare to tech talk", isDone = true),
            Todo(id = 2, title = "Share with the team", isDone = false)
        )

        get("/") {
            call.respondText("Hello World!")
        }

        get("/todos") {
            call.respond(todos)
        }

        get("/todos/{id}") {

        }

        post("/todos") {

        }

        put("/todos/{id}") {

        }

        delete("/todos/{id}") {

        }
    }
}
