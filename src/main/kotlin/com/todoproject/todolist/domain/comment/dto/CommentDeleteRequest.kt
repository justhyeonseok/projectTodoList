package com.todoproject.todolist.domain.comment.dto

data class CommentDeleteRequest(
    val writer: String,
    val password: String
)