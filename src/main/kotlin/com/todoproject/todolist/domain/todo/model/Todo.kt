package com.todoproject.todolist.domain.todo.model

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todolist")
class Todo(
    @Column(name = "title", nullable = false)
    var title: String,

    @Column(name = "content")
    var content: String? = null,

    @Column(name = "date", nullable = false)
    var date: LocalDateTime,

    @Column(name = "writer")
    var writer: String? = null
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

