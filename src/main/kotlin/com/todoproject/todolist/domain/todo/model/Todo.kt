package com.todoproject.todolist.domain.todo.model

import com.todoproject.todolist.domain.comment.model.Comment
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "todolist")
class Todo(
    @Column(name = "title", nullable = false)
    var title: String? = null,

    @Column(name = "content")
    var content: String? = null,

    @Column(name = "writer")
    var writer: String? = null,
    @OneToMany(
        mappedBy = "todo", fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true
    ) // 생명주기 결정
    var comments: MutableList<Comment> = mutableListOf()
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(name = "date", nullable = false)
    val createAt: LocalDateTime = LocalDateTime.now()

    @Column(name = "complete")
    var completed: Boolean = false

    val iscompleted: Boolean
        get() = completed

    fun complete() {
        completed = true
    }
}




