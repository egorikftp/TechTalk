package com.techtalk.repository

import com.techtalk.entity.Todo
import com.techtalk.entity.TodoDraft

interface TodoRepository {

    fun getAllTodos(): List<Todo>

    fun getTodo(id: Int): Todo?

    fun addTodo(draft: TodoDraft): Todo

    fun removeTodo(id: Int): Boolean

    fun updateTodo(id: Int, draft: TodoDraft): Boolean
}