package com.todoproject.todolist.domain.todo.dto.response

import java.time.LocalDateTime


data class TodoResponse(

    val id: Long,
    val title: String,
    val content: String?,
    val date: LocalDateTime,
    val writer: String?,
    val completed: Boolean
)


