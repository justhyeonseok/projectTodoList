package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.CommentLikeUser
import org.springframework.data.jpa.repository.JpaRepository

interface CommentLikeUserRepository : JpaRepository<CommentLikeUser, Long>, CustomCommentLikeUserRepository {

}