package com.todoproject.todolist.domain.comment.dto

import com.todoproject.todolist.domain.user.model.User

data class CreateCommentRequest(
    val content: String?
)
