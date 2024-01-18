package com.todoproject.todolist.domain.comment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.todoproject.todolist.domain.todo.model.Todo
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "content")
    var content: String? = null,

    @Column(name = "writer")
    var writer: String? = null,

    @Column(name = "password", nullable = false)
    var password: String,

    @ManyToOne(fetch = FetchType.LAZY) // 지연로딩
    @JoinColumn(name = "todo_id", nullable = false)
    @JsonIgnore
    var todo: Todo
) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    fun changeContent(content: String?) {
        this.content = content
    }
}
