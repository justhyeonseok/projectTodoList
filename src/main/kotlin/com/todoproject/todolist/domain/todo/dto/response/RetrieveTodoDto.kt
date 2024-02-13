package com.todoproject.todolist.domain.todo.dto.response

import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.Profile
import java.time.LocalDateTime

data class RetrieveTodoDto(
    val id: Long,
    val title: String?,
    val content: String?,
    val createAt: LocalDateTime,
    val author: Profile,
    val completed: Boolean,
    val commentList: List<CommentDto>
) {
    companion object {
        fun from(todo: Todo): RetrieveTodoDto {
            return RetrieveTodoDto(
                id = todo.id!!,
                title = todo.title,
                content = todo.content,
                createAt = todo.createAt,
                author = todo.author.authorName,
                completed = todo.completed,
                commentList = todo.comments.map {
                    CommentDto(
                        it.id!!,
                        it.content,
                        it.author.authorName,
                        it.count
                    )
                }
            )
        }
    }
}


