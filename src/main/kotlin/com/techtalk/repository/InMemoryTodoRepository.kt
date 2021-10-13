package com.techtalk.repository

import com.techtalk.entity.Todo

class InMemoryTodoRepository : TodoRepository {

    private val todos = listOf(
        Todo(id = 1, title = "Prepare to tech talk", isDone = true),
        Todo(id = 2, title = "Share with the team", isDone = false)
    )

    override fun getAllTodos(): List<Todo> {
        return todos
    }

    override fun getTodo(id: Int): Todo? {
        return todos.firstOrNull { it.id == id }
    }
}