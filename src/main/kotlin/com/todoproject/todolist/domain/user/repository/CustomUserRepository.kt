package com.todoproject.todolist.domain.user.repository

import com.todoproject.todolist.domain.user.model.User

interface CustomUserRepository {
    fun searchNickName(nickName: String): User?
}