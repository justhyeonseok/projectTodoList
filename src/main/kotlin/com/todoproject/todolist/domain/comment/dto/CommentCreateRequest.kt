package com.todoproject.todolist.domain.comment.dto

import com.todoproject.todolist.domain.comment.model.Comment

data class CommentCreateRequest(
    val content: String?,
    val writer: String?,
    val password: String
)
