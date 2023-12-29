package com.todoproject.todolist.domain.todo.service

import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoResponse

interface TodoService {

    // 할일 목록 조회
    fun getAllTodoList(): List<TodoResponse>

    // 할일 단건 조회
    fun getTodoById(boardId: Long): TodoResponse

    // 할일 생성
    fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse

    // 할일 수정
    fun updateTodo(boardId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse

    // 할일 삭제
    fun deleteTodo(boardId: Long)

}