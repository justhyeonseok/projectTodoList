package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository : JpaRepository<Comment, Long> {
    fun findByIdAndTodoId(commentId: Long, todoId: Long): Comment?

}