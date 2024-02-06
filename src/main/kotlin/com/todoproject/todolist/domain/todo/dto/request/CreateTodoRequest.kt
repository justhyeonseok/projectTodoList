package com.todoproject.todolist.domain.todo.dto.request

import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.User
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size


data class CreateTodoRequest(
    @field: NotBlank(message = "제목을 입력하세요")
    @Size(min = 1, max = 500, message = "제목은 1자 이상 500 이하로 작성해주세요")
    val title: String?,
    @Size(max = 5000, message = "5000글자까지 작성 가능합니다")
    val content: String?
) {
    fun to(author: User): Todo {
        return Todo(
            title = title,
            content = content,
            author = author
        )
    }
}