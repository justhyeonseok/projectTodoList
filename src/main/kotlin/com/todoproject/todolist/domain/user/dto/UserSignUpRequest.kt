package com.todoproject.todolist.domain.user.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern


data class UserSignUpRequest(
    @field: NotBlank(message = "이름을 입력해주세요")
    @field: Pattern(
        regexp = "^[a-zA-Z0-9]{3,12}$",
        message = " 이름은 3~12 글자만 가능합니다"
    )
    val authorName: String,
    @field: Email(message = "올바른 이메일 형식을 입력해주세요")
    @field: NotBlank(message = "이메일을 입력해주세요")
    val userEmail: String,
    @field: NotBlank(message = "비밀번호를 입력해주세요")
    @field: Pattern(
        regexp = "^[a-zA-Z0-9!@#$%^&*]{4,12}",
        message = "비밀번호는 4자리이상 12자리 이하로 설정해주세요!"
    )
    val password: String,
    @field: NotBlank(message = "비밀번호를 한번더 입력해주세요")
    val confirmPassword: String,
    @field: NotBlank(message = "role 을 입력해주세요 입력방식: 'MEMBER' ")
    val userRole: String
)