package com.todoproject.todolist.domain.todo.dto.response

import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.todo.model.Todo
import java.time.LocalDateTime


data class TodoDto(
    val id: Long,
    val title: String?,
    val content: String?,
    val createAt: LocalDateTime,
    val writer: String?,
    val completed: Boolean
) {
    companion object {
        fun from(todo: Todo): TodoDto {
            return TodoDto(
                id = todo.id!!,
                title = todo.title,
                content = todo.content,
                createAt = todo.createAt,
                writer = todo.writer,
                completed = todo.completed
            )
        }
    }
}


