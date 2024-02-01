package com.todoproject.todolist.domain.todo.repository

import com.todoproject.todolist.domain.todo.model.QTodo
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class TodoRepositoryImpl : QueryDslSupport(), CustomTodoRepository {
    private val todo = QTodo.todo
    override fun searchTodoListByTitle(title: String): List<Todo> {
        return queryFactory.selectFrom(todo)
            .where(todo.title.containsIgnoreCase(title))
            .fetch()
    }

}