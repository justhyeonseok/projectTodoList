package com.todoproject.todolist.domain.todo.dto.response


import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.Profile
import java.time.LocalDateTime


data class TodoDto(
    val id: Long,
    val title: String?,
    val content: String?,
    val createAt: LocalDateTime,
    val author: Profile,
    val completed: Boolean
) {
    companion object {
        fun from(todo: Todo): TodoDto {
            return TodoDto(
                id = todo.id!!,
                title = todo.title,
                content = todo.content,
                createAt = todo.createAt,
                author = todo.author.authorName,
                completed = todo.completed
            )
        }
    }
}


