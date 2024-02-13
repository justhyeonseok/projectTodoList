package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.model.CommentLikeUser
import com.todoproject.todolist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeUserRepository : JpaRepository<CommentLikeUser, Long>, CustomCommentLikeUserRepository {
    fun findByCommentAndUser(comment: Comment, user: User): CommentLikeUser?
}