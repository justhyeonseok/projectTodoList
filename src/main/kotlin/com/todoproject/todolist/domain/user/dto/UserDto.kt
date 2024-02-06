package com.todoproject.todolist.domain.user.dto

import com.todoproject.todolist.domain.user.model.User

data class UserDto(
    var id: Long?,
    val authorName: String,
    val userEmail: String,
    val role: String = "MEMBER"
) {
    companion object {
        fun from(user: User): UserDto {
            return UserDto(
                id = user.id!!,
                authorName = user.authorName.nickname,
                userEmail = user.userEmail,
                role = "MEMBER"
            )
        }
    }
}