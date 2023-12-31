package com.todoproject.todolist.domain.todo.service

import com.todoproject.todolist.domain.comment.dto.CommentResponse
import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoResponse

interface TodoService {

    // 할일 목록 조회
    fun getAllTodoList(completed: Boolean?): List<TodoResponse>

    // 할일 단건 조회
    fun getTodoById(todoId: Long): TodoResponse

    // 할일 생성
    fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse

    // 할일 수정
    fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse

    // 할일 삭제
    fun deleteTodo(todoId: Long)

    // 할일 완료
    fun completeTodo(todoId: Long): TodoResponse

}