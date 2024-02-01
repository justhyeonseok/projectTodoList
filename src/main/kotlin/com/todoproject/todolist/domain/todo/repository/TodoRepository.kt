package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long>, CustomTodoRepository {
    fun findAllByOrderByCreateAtDesc(): List<TodoDto>
    fun findAllByOrderByCreateAtAsc(): List<TodoDto>
    fun findByIdAndAuthor(todoId: Long, userId: User): Todo?
}