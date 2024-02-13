package com.todoproject.todolist.domain.comment.dto

import com.todoproject.todolist.domain.comment.model.CommentLikeUser

data class CommentLikeDto(
    val likeStatus: Boolean
) {
    companion object {
        fun status(commentLikeUser: CommentLikeUser): CommentLikeDto {
            return CommentLikeDto(
                likeStatus = commentLikeUser.like
            )
        }
    }
}