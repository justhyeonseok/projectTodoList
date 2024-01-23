package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest
import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.repository.CommentRepository
import com.todoproject.todolist.domain.exception.TodoNotFoundException
import com.todoproject.todolist.domain.exception.UserNotFoundException
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import com.todoproject.todolist.domain.user.repository.UserRepository
import com.todoproject.todolist.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) : CommentService {
    @Transactional
    override fun createComment(
        todoId: Long,
        createCommentRequest: CreateCommentRequest,
        user: UserPrincipal
    ): CommentDto {
        val comment = Comment(
            content = createCommentRequest.content,
            author = userRepository.findByIdOrNull(user.id) ?: throw UserNotFoundException("user", user),
            todo = todoRepository.findByIdOrNull(todoId) ?: throw TodoNotFoundException("todo", todoId)
        )
        val saveComment = commentRepository.save(comment)
        return CommentDto.from(saveComment)
    }

    @Transactional
    override fun updateComment(
        todoId: Long, commentId: Long, updateCommentRequest: UpdateCommentRequest, user: UserPrincipal
    ): CommentDto {
        val comment =
            commentRepository.findByIdAndTodoIdAndAuthor(commentId, todoId, userRepository.findByIdOrNull(user.id))
                ?: throw TodoNotFoundException("todo or userId", todoId)
        comment.changeContent(updateCommentRequest.content)
        val updateComment = commentRepository.save(comment)
        return CommentDto.from(updateComment)
    }

    @Transactional
    override fun deleteComment(
        todoId: Long,
        commentId: Long,
        user: UserPrincipal
    ) {
        val delete =
            commentRepository.findByIdAndTodoIdAndAuthor(todoId, commentId, userRepository.findByIdOrNull(user.id))
                ?: throw TodoNotFoundException(
                    "todo, comment",
                    todoId
                )
        commentRepository.delete(delete)
    }
}


