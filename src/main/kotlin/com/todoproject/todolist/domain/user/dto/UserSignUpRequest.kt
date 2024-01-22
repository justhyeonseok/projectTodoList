package com.todoproject.todolist.domain.user.dto


data class UserSignUpRequest(
    val authorName: String,
    val userEmail: String,
    val password: String,
    val userRole: String
)