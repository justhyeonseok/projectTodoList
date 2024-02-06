package com.todoproject.todolist.domain.todo.service

import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.RetrieveTodoDto
import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.infra.security.UserPrincipal

interface TodoService {
    fun searchTodoList(title: String): List<TodoDto>

    // 할일 목록 조회
    fun getByTodoList(): List<TodoDto>

    // 할일 단건 조회
    fun getTodoById(todoId: Long, user: UserPrincipal): RetrieveTodoDto

    // 할일 생성
    fun createTodo(createTodoRequest: CreateTodoRequest, user: UserPrincipal): TodoDto

    // 할일 수정
    fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest, user: UserPrincipal): TodoDto

    // 할일 삭제
    fun deleteTodo(todoId: Long, user: UserPrincipal)

    // 할일 완료
    fun completeTodo(todoId: Long, user: UserPrincipal): TodoDto

}