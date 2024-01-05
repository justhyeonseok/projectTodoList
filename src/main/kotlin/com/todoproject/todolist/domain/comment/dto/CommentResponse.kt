package com.todoproject.todolist.domain.comment.dto


data class CommentResponse(
    val id: Long,
    val content: String?,
    val writer: String?
)