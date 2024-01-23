package com.todoproject.todolist.domain.exception

import com.todoproject.todolist.infra.security.UserPrincipal

data class UserNotFoundException(val modelName: String, val id: UserPrincipal) : RuntimeException(
    "Model $modelName not found with given id: $id"
)