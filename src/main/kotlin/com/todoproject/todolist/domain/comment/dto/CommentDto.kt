package com.todoproject.todolist.domain.comment.dto

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.exception.WriterNotMatchedException
import org.springframework.data.jpa.domain.AbstractPersistable_.id


data class CommentDto(
    val id: Long,
    val content: String?,
    val writer: String?
) {
    companion object {
        fun from(comment: Comment): CommentDto {
            return CommentDto(
                id = comment.id!!,
                content = comment.content,
                writer = comment.writer
            )
        }
    }
}