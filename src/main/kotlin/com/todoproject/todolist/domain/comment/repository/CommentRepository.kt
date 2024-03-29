package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndTodoIdAndAuthor(commentId: Long, todoId: Long, userId: User?): Comment?

}