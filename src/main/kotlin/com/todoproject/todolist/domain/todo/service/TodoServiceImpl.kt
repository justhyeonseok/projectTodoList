package com.todoproject.todolist.domain.todo.service


import com.todoproject.todolist.domain.todo.dto.request.CreateTodoRequest
import com.todoproject.todolist.domain.todo.dto.request.UpdateTodoRequest
import com.todoproject.todolist.domain.todo.dto.response.TodoDto
import com.todoproject.todolist.domain.exception.ModelNotFoundException
import com.todoproject.todolist.domain.todo.dto.response.RetrieveTodoDto
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import com.todoproject.todolist.domain.user.repository.UserRepository
import com.todoproject.todolist.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodoServiceImpl(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
) : TodoService {

    override fun searchTodoList(title: String): List<TodoDto> {
        return todoRepository.searchTodoListByTitle(title).map { TodoDto.from(it) }

    }

    @Transactional(readOnly = true)
    override fun getByTodoList(): List<TodoDto> {
        return todoRepository.getByTodoListByAsc().map { TodoDto.from(it) }
    }

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('MEMBER')")
    override fun getTodoById(todoId: Long): RetrieveTodoDto {
        val todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
        return todo.let { RetrieveTodoDto.from(it) }
        //return TodoDto.from(tod0) 와 같음 tod0?.let 이라고 한다면 널이라면 널을 반환
    }

    @Transactional
    @PreAuthorize("hasRole('MEMBER')")
    override fun createTodo(createTodoRequest: CreateTodoRequest, user: UserPrincipal): TodoDto {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("userId", user.id)
        val savedTodo = todoRepository.save(createTodoRequest.to(users))
        return TodoDto.from(savedTodo)
    }

    @Transactional
    @PreAuthorize("hasRole('MEMBER')")
    override fun updateTodo(todoId: Long, updateTodoRequest: UpdateTodoRequest, user: UserPrincipal): TodoDto {
        val todo = todoRepository.findByIdAndAuthor(
            todoId,
            userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("userId", user.id)
        ) ?: throw ModelNotFoundException("todo", todoId)
        todo.changeTodo(updateTodoRequest)
        val savedTodo = todoRepository.save(todo)
        return TodoDto.from(savedTodo)
    }

    @Transactional
    @PreAuthorize("hasRole('MEMBER')")
    override fun deleteTodo(todoId: Long, user: UserPrincipal) {


        val todo = todoRepository.findByIdAndAuthor(
            todoId,
            userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("userId", user.id)
        ) ?: throw ModelNotFoundException("todo", todoId)
        todoRepository.delete(todo)

    }

    @Transactional
    @PreAuthorize("hasRole('MEMBER')")
    override fun completeTodo(todoId: Long, user: UserPrincipal): TodoDto {
        val todo = todoRepository.findByIdAndAuthor(
            todoId,
            userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("userId", user.id)
        ) ?: throw ModelNotFoundException("todo", todoId)
        todo.complete()
        todoRepository.save(todo)
        return TodoDto.from(todo)
    }
}