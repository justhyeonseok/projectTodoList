package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.DeleteCommentRequest
import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest

interface CommentService {
    fun createComment(todoId: Long, createCommentRequest: CreateCommentRequest): CommentDto

    fun updateComment(todoId: Long, commentId: Long, updateCommentRequest: UpdateCommentRequest): CommentDto

    fun deleteComment(todoId: Long, commentId: Long, deleteCommentRequest: DeleteCommentRequest)
}