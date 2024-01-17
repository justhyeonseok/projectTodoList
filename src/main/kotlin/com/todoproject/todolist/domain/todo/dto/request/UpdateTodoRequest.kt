package com.todoproject.todolist.domain.todo.dto.request

import com.todoproject.todolist.domain.todo.model.Todo


data class UpdateTodoRequest(
    val title: String?,
    val content: String?,
    val writer: String?
) {
    fun to(): Todo {
        return Todo(
            title = title,
            content = content,
            writer = writer
        )
    }
}