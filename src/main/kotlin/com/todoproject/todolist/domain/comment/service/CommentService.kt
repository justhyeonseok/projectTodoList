package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CommentDeleteRequest
import com.todoproject.todolist.domain.comment.dto.CommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentResponse

interface CommentService {
    fun createComment(todoId: Long, commentRequest: CommentRequest): CommentResponse

    fun updateComment(todoId: Long, commentId: Long, commentRequest: CommentRequest): CommentResponse

    fun deleteComment(todoId: Long, commentId: Long, commentDeleteRequest: CommentDeleteRequest)
}