package com.todoproject.todolist.domain.user.controller

import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserLoginRequest
import com.todoproject.todolist.domain.user.dto.UserLoginResponse
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest
import com.todoproject.todolist.domain.user.service.UserService
import com.todoproject.todolist.domain.user.service.UserServiceImpl
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpHeaders.SET_COOKIE
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(val userService: UserService) {
    @PostMapping("/signup")
    @Operation(summary = "회원가입")
    fun signUpUser(@Valid @RequestBody userSignUpRequest: UserSignUpRequest): ResponseEntity<UserDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUpUser(userSignUpRequest))
    }

    @PostMapping("/login")
    @Operation(summary = "로그인")
    fun loginUser(@RequestBody userLoginRequest: UserLoginRequest): ResponseEntity<UserLoginResponse> {
        val loginJwtToken = userService.loginUser(userLoginRequest)
        return ResponseEntity
            .status(HttpStatus.OK)
            .header("Set-Cookie", "Set-Cookie=${loginJwtToken.accessToken}")
            .body(loginJwtToken)
    }
    @PostMapping("/logout")
    @Operation(summary = "로그아웃")
    fun logout() {
    }
    @GetMapping("/signup")
    @Operation(summary = "이름 중복검사")
    fun existsByUserName(@RequestParam nickName: String): ResponseEntity<String> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.existsByUserName(nickName))
    }
}