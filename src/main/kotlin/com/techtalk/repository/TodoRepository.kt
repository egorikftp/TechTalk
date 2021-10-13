package com.techtalk.repository

import com.techtalk.entity.Todo

interface TodoRepository {

    fun getAllTodos(): List<Todo>

    fun getTodo(id: Int): Todo?
}