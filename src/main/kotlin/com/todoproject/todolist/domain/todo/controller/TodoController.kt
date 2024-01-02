package com.todoproject.todolist.domain.todo.controller

import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoResponse
import com.todoproject.todolist.domain.todo.service.TodoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/boards")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    // 할일 목록조회
    @GetMapping()
    fun getTodoList(): ResponseEntity<List<TodoResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList())
    }

    // 할일 단건 조회
    @GetMapping("/{boardId}")
    fun getTodo(@PathVariable boardId: Long): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(boardId))
    }

    //할일 생성
    @PostMapping()
    fun creatTodo(@RequestBody creatTodoRequest: CreateTodoRequest): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(creatTodoRequest))
    }

    // 할일 수정
    @PutMapping("/{boardId}")
    fun updateTodo(@PathVariable boardId: Long, @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(boardId, updateTodoRequest))
    }

    // 할일 삭제
    @DeleteMapping("/{boardId}")
    fun deleteTodo(@PathVariable boardId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(boardId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}