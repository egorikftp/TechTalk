package com.techtalk.plugins

import com.techtalk.repository.InMemoryTodoRepository
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
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
        val repository = InMemoryTodoRepository()

        get("/") {
            call.respondText("Hello World!")
        }

        get("/todos") {
            call.respond(repository.getAllTodos())
        }

        get("/todos/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()

            if (id == null) {
                call.respond(
                    status = HttpStatusCode.BadRequest,
                    message = "id parameter invalid"
                )

                return@get
            }

            val todo = repository.getTodo(id)

            if (todo == null) {
                call.respond(
                    status = HttpStatusCode.NotFound,
                    message = "todo with id $id not found"
                )
            } else {
                call.respond(todo)
            }
        }

        post("/todos") {

        }

        put("/todos/{id}") {

        }

        delete("/todos/{id}") {

        }
    }
}
