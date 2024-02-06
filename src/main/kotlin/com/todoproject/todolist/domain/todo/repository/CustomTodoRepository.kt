package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.model.Todo

interface CustomTodoRepository {
    fun searchTodoListByTitle(title: String): List<Todo>
    fun getByTodoListByAsc(): List<Todo>
}