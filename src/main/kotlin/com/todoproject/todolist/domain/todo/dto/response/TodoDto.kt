package com.todoproject.todolist.domain.todo.dto.response

import com.todoproject.todolist.domain.comment.dto.CommentResponse
import com.todoproject.todolist.domain.todo.model.Todo
import java.time.LocalDateTime


data class TodoDto(
    val id: Long,
    val title: String?,
    val content: String?,
    val createAt: LocalDateTime = LocalDateTime.now(),
    val writer: String?,
    val commentList: List<CommentResponse>
) {
    companion object {
        fun from(todo: Todo): TodoDto {
            return TodoDto(
                id = todo.id!!,
                title = todo.title,
                content = todo.content,
                createAt = todo.createAt,
                writer = todo.writer,
                commentList = todo.comments.map {
                    CommentResponse(
                        it.id!!,
                        it.content,
                        it.writer
                    )
                }
            )
        }
    }
}


