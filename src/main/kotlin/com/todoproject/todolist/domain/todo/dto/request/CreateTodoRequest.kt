package com.todoproject.todolist.domain.todo.dto.request

import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.User


data class CreateTodoRequest(
    val title: String?,
    val content: String?
) {
    fun to(author: User): Todo {
        return Todo(
            title = title,
            content = content,
            author = author
        )
    }
}