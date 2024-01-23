package com.todoproject.todolist.domain.user.model

import jakarta.persistence.*

@Entity
@Table(name = "app_user")
class User(
    @Embedded
    val authorName: Profile,

    @Column(name = "email")
    val userEmail: String,

    @Column(name = "password")
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    val userRole: UserRole,
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}