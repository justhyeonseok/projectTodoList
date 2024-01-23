package com.todoproject.todolist.domain.comment.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.todoproject.todolist.domain.todo.model.Todo
import com.todoproject.todolist.domain.user.model.User
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "content")
    var content: String? = null,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val author: User,

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

    fun checkAuthorization(user: User) {
        if (this.author.id != user.id) {
            throw Exception("No permission")
        }
    }
}
