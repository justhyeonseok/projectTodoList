package com.todoproject.todolist.domain.todo.service


import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoResponse
import com.todoproject.todolist.domain.todo.exception.ModelNotFoundException
import com.todoproject.todolist.domain.todo.model.TodoList
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import jakarta.transaction.Transactional
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class TodoServiceImpl(private val todoRepository: TodoRepository): TodoService {

    override fun getAllTodoList(): List<TodoResponse> {
        return todoRepository.findAll().map { it.toResponse() }
    }


    override fun getTodoById(boardId: Long): TodoResponse {
        val todo = todoRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("todo", boardId)
        return todo.toResponse()
    }

    @Transactional
    override fun createTodo(createTodoRequest: CreateTodoRequest): TodoResponse {
        return todoRepository.save(
            TodoList(
                title = createTodoRequest.title,
                content = createTodoRequest.content,
                date = createTodoRequest.date,
                createrName = createTodoRequest.createrName
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodo(boardId: Long, updateTodoRequest: UpdateTodoRequest): TodoResponse {
        val todo = todoRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("todo", boardId)
        val (title, content, date, createrName) = updateTodoRequest

        todo.title = title
        todo.content = content
        todo.date = date
        todo.createrName = createrName

        return todoRepository.save(todo).toResponse()
    }

    @Transactional
    override fun deleteTodo(boardId: Long) {
        val todo = todoRepository.findByIdOrNull(boardId) ?: throw ModelNotFoundException("todo", boardId)

    }

    fun TodoList.toResponse(): TodoResponse {
        return TodoResponse(
            id = id!!,
            title = title,
            content = content,
            date = date,
            createrName = createrName
        )
    }
}

