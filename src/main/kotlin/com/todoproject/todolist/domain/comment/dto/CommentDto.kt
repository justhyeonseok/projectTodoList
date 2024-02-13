package com.todoproject.todolist.domain.comment.dto

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.user.model.Profile


data class CommentDto(
    val id: Long,
    val content: String?,
    val author: Profile,
    val count: Int
) {
    companion object {
        fun from(comment: Comment): CommentDto {
            return CommentDto(
                id = comment.id!!,
                content = comment.content,
                author = comment.author.authorName,
                count = comment.count
            )
        }
    }
}