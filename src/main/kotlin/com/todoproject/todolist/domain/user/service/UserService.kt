package com.todoproject.todolist.domain.user.service

import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest

interface UserService {
    fun signUpUser(userSignUpRequest: UserSignUpRequest): UserDto
}