package com.todoproject.todolist.domain.exception

enum class ResultCode(val msg: String) {
    SUCCESS("정상"),
    ERROR("에러")
}