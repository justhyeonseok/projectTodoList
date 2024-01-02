package com.todoproject.todolist.domain.todo.dto.request

import java.time.LocalDateTime

data class UpdateTodoRequest(

    val title: String,
    val content: String?,
    val date: LocalDateTime,
    val writer: String?
)