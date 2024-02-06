package com.todoproject.todolist.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank


data class UserLoginRequest(
    @field: NotBlank(message = "이메일을 입력해주세요.")
    @field: Email(message = "이메일 형식이 올바르지 않습니다.")
    val userEmail: String,
    @field: NotBlank(message = "비밀번호를 입력해주세요")
    val password: String,
    @field: NotBlank(message = "유저 role 이 올바르지 않습니다.")
    val userRole: String = "MEMBER"
)