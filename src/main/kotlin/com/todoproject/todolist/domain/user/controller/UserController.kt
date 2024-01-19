package com.todoproject.todolist.domain.user.controller

import com.todoproject.todolist.domain.user.dto.UserDto
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
@RequestMapping("/users")
class UserController(val userService: UserService) {
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    fun signUpUser(@RequestBody userSignUpRequest: UserSignUpRequest): ResponseEntity<UserDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUpUser(userSignUpRequest))
    }
}