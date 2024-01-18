package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
    fun findAllByOrderByCreateAtDesc(): List<TodoDto>
    fun findAllByOrderByCreateAtAsc(): List<TodoDto>
}