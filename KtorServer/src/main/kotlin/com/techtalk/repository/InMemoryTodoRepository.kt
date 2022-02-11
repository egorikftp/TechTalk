package com.techtalk.repository

import com.techtalk.entity.Todo
import com.techtalk.entity.TodoDraft

class InMemoryTodoRepository : TodoRepository {

    private val todos = mutableListOf(
        Todo(id = 1, title = "Prepare to tech talk", isDone = true),
        Todo(id = 2, title = "Share with the team", isDone = false)
    )

    override fun getAllTodos(): List<Todo> {
        return todos
    }

    override fun getTodo(id: Int): Todo? {
        return todos.firstOrNull { it.id == id }
    }

    override fun addTodo(draft: TodoDraft): Todo {
        val todo = Todo(
            id = todos.size + 1,
            title = draft.title,
            isDone = draft.isDone
        )

        todos.add(todo)

        return todo
    }

    override fun removeTodo(id: Int): Boolean {
        return todos.removeIf { it.id == id }
    }

    override fun updateTodo(id: Int, draft: TodoDraft): Boolean {
        val todo = todos.firstOrNull { it.id == id } ?: return false

        todo.isDone = draft.isDone
        todo.title = draft.title

        return true
    }
}