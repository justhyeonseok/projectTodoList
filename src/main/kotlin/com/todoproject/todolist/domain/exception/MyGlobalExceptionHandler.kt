package com.todoproject.todolist.domain.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class MyGlobalExceptionHandler {
    @ExceptionHandler(TodoNotFoundException::class)
    fun todoCommentNotFoundException(e: TodoNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message, errorCode = "Todo를 찾을 수 없습니다."))
    }

    @ExceptionHandler(WriterNotMatchedException::class)
    fun unAuthorizedException(e: WriterNotMatchedException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message, errorCode = "이름이 틀립니다."))
    }

    @ExceptionHandler(IncorrectPasswordException::class)
    fun unAuthorizedException(e: IncorrectPasswordException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message, errorCode = "패스워드가 틀립니다."))
    }

    @ExceptionHandler(InvalidCredentialException::class)
    fun handleInvalidCredentialException(e: InvalidCredentialException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.UNAUTHORIZED)
            .body(ErrorResponse(message = e.message, errorCode = "패스워드가 틀립니다."))
    }

    @ExceptionHandler(UserNotFoundException::class)
    fun userNotFoundException(e: UserNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse(message = e.message, errorCode = "user를 찾을 수 없습니다."))
    }
}
