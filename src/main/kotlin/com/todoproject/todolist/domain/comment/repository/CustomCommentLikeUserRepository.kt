package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.model.CommentLikeUser
import com.todoproject.todolist.domain.user.model.User

interface CustomCommentLikeUserRepository {
    fun commentLikeUser(comment: Comment, user: User): CommentLikeUser?
}