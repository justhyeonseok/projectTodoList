package com.todoproject.todolist.domain.todo.model

import com.todoproject.todolist.domain.todo.dto.response.TodoResponse
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
    var writer: String? = null,

    // 새로작성된 완료엔티티
    @Column(name = "complete")
    var completed: Boolean = false
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    fun toResponse(): TodoResponse {
        return TodoResponse(
            id = id!!,
            title = title,
            content = content,
            date = date,
            writer = writer,
            completed = completed
        )
    }

}


