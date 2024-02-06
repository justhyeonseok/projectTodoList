package com.todoproject.todolist.domain.user.service

import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserLoginRequest
import com.todoproject.todolist.domain.user.dto.UserLoginResponse
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest

interface UserService {
    fun signUpUser(userSignUpRequest: UserSignUpRequest): UserDto
    fun loginUser(userLoginRequest: UserLoginRequest): UserLoginResponse
    fun existsByUserName(nickName: String): String
}