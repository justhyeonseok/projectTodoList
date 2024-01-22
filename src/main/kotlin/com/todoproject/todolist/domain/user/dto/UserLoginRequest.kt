package com.todoproject.todolist.domain.user.dto


data class UserLoginRequest(
    val userEmail: String,
    val password: String,
    val userRole: String
)