package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.model.Todo
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<Todo, Long> {
}