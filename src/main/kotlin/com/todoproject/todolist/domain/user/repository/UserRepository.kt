package com.todoproject.todolist.domain.user.repository

import com.todoproject.todolist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun existsByUserEmail(userEmail: String): Boolean
    fun findByUserEmail(userEmail: String): User?
}