package com.todoproject.todolist.domain.user.model

import com.todoproject.todolist.domain.user.dto.UserSignUpRequest
import jakarta.persistence.*
import org.springframework.security.crypto.password.PasswordEncoder

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