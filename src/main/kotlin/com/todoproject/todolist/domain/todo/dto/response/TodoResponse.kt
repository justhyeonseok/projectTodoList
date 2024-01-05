package com.todoproject.todolist.domain.todo.dto.response

import com.todoproject.todolist.domain.comment.dto.CommentResponse
import java.time.LocalDateTime


data class TodoResponse(
    val id: Long,
    val title: String,
    val content: String?,
    val date: LocalDateTime,
    val writer: String?,
    val completed: Boolean,
    val commentList: List<CommentResponse>
)


