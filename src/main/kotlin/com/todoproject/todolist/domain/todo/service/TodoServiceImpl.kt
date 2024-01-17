package com.todoproject.todolist.domain.todo.service


import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.exception.TodoNotFoundException
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(private val todoRepository: TodoRepository): TodoService {

    @Transactional(readOnly = true)
    override fun getAllTodoList(completed: Boolean?): List<TodoDto> {
        val todos: List<Todo> = if (completed != null) {
            todoRepository.findByCompleted(completed)
        } else {
            todoRepository.findAll()
        }
        return todos.map { TodoDto.from(it) }
    }


    @Transactional(readOnly = true)
    override fun getTodoById(todoId: Long): TodoDto {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        return todo.let { TodoDto.from(it) }
        //return TodoDto.from(tod0) 와 같음 tod0?.let 이라고 한다면 널이라면 널을 반환
    }

    @Transactional
    override fun createTodo(createTodoRequest: CreateTodoRequest): TodoDto {
        val savedTodo = todoRepository.save(createTodoRequest.to())
        return TodoDto.from(savedTodo)
    }

    @Transactional
    override fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest): TodoDto {
        todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        val savedTodo = todoRepository.save(updateTodoRequest.to())
        return TodoDto.from(savedTodo)
    }

    @Transactional
    override fun deleteTodo(todoId: Long) {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        todoRepository.delete(todo)

    }

    @Transactional
    override fun completeTodo(todoId: Long): TodoDto {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        todo.complete()
        todoRepository.save(todo)
        return TodoDto.from(todo)
    }
}