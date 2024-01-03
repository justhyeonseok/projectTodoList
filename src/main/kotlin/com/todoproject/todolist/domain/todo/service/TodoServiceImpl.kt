package com.todoproject.todolist.domain.todo.service


import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoResponse
import com.todoproject.todolist.domain.exception.TodoNotFoundException
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(private val todoRepository: TodoRepository): TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }


    override fun getTodoById(todoId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            Todo(
                title = createTodoRequest.title,
                content = createTodoRequest.content,
                date = createTodoRequest.date,
                writer = createTodoRequest.writer
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        val (title, content, date, writer) = updateTodoRequest

        todo.title = title
        todo.content = content
        todo.date = date
        todo.writer = writer

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)

    }

    fun Todo.toResponse(): TodoResponse {
        return TodoResponse(
            id = id!!,
            title = title,
            content = content,
            date = date,
            writer = writer
        )
    }
}

