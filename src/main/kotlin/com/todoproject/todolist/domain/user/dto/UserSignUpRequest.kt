package com.todoproject.todolist.domain.user.dto

import com.todoproject.todolist.domain.user.model.User

data class UserSignUpRequest(
    val authorName: String,
    val userEmail: String,
    val password: String
) {
    fun to(): User {
        return User(
            authorName = authorName,
            userEmail = userEmail,
            password = password
        )
    }
}