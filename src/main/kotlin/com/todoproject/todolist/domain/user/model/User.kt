package com.todoproject.todolist.domain.user.model

import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
    @Column
    val authorName: String,
    @Column
    val userEmail: String,
    @Column
    val password: String
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}