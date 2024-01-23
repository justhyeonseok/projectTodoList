package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest
import com.todoproject.todolist.infra.security.UserPrincipal

interface CommentService {
    fun createComment(todoId: Long, createCommentRequest: CreateCommentRequest, user: UserPrincipal): CommentDto

    fun updateComment(
        todoId: Long, commentId: Long, updateCommentRequest: UpdateCommentRequest,
        user: UserPrincipal
    ): CommentDto

    fun deleteComment(todoId: Long, commentId: Long, user: UserPrincipal)
}