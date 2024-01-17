package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CommentDeleteRequest
import com.todoproject.todolist.domain.comment.dto.CommentCreateRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto

interface CommentService {
    fun createComment(todoId: Long, commentRequest: CommentCreateRequest): CommentDto

    fun updateComment(todoId: Long, commentId: Long, commentRequest: CommentCreateRequest): CommentDto

    fun deleteComment(todoId: Long, commentId: Long, commentDeleteRequest: CommentDeleteRequest)
}