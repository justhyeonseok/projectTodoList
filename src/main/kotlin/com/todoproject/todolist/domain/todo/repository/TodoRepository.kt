package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.model.TodoList
import org.springframework.data.jpa.repository.JpaRepository

interface TodoRepository : JpaRepository<TodoList, Long> {
}