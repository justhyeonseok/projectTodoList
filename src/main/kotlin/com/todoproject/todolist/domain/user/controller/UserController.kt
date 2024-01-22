package com.todoproject.todolist.domain.user.controller

import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserLoginRequest
import com.todoproject.todolist.domain.user.dto.UserLoginResponse
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest
import com.todoproject.todolist.domain.user.service.UserService
import com.todoproject.todolist.domain.user.service.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    fun signUpUser(@RequestBody userSignUpRequest: UserSignUpRequest): ResponseEntity<UserDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUpUser(userSignUpRequest))
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    fun loginUser(@RequestBody userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.loginUser(userLoginRequest))
    }
}