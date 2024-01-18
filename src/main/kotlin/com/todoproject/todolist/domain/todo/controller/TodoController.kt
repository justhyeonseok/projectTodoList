package com.todoproject.todolist.domain.todo.controller

import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.RetrieveTodoDto
import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.todo.service.TodoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todos")
@RestController
class TodoController(
    private val todoService: TodoService
) {

    // 할일 목록조회
    @GetMapping()
    @Operation(summary = "할일 완료 목록 전체 조회 ")
    fun getTodoList(
        @RequestParam sort: String?
    ): ResponseEntity<List<TodoDto>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getAllTodoList(sort))
    }

    // 할일 단건 조회
    @Operation(summary = "할일 단건 조회", description = "할일 단건을 조회합니다.")
    @GetMapping("/{todoId}")
    fun getTodo(@PathVariable todoId: Long): ResponseEntity<RetrieveTodoDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.getTodoById(todoId))
    }

    //할일 생성
    @Operation(summary = "할일 카드 생성")
    @PostMapping()
    fun createTodo(@RequestBody creatTodoRequest: CreateTodoRequest): ResponseEntity<TodoDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todoService.createTodo(creatTodoRequest))
    }

    // 할일 수정
    @Operation(summary = "할일 카드 수정")
    @PutMapping("/{todoId}")
    fun updateTodo(
        @PathVariable todoId: Long, @RequestBody updateTodoRequest: UpdateTodoRequest
    ): ResponseEntity<TodoDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.updateTodo(todoId, updateTodoRequest))
    }

    // 할일 삭제
    @Operation(summary = "할일 카드 삭제")
    @DeleteMapping("/{todoId}")
    fun deleteTodo(@PathVariable todoId: Long): ResponseEntity<Unit> {
        todoService.deleteTodo(todoId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    //할일 완료
    @Operation(summary = "할일 카드 완료")
    @PatchMapping("/{todoId}/complete")
    fun completeTodo(@PathVariable todoId: Long): ResponseEntity<TodoDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todoService.completeTodo(todoId))
    }
}
