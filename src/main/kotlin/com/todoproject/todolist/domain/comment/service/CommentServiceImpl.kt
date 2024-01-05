package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CommentDeleteRequest
import com.todoproject.todolist.domain.comment.dto.CommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentResponse
import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.repository.CommentRepository
import com.todoproject.todolist.domain.exception.TodoNotFoundException
import com.todoproject.todolist.domain.exception.IncorrectPasswordException
import com.todoproject.todolist.domain.exception.WriterNotMatchedException
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import java.io.Writer

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {
    override fun createComment(todoId: Long, commentRequest: CommentRequest): CommentResponse {
        val todo = todoRepository.findById(todoId).orElseThrow { TodoNotFoundException("todo", todoId) }
        val comment = Comment(
            content = commentRequest.content,
            writer = commentRequest.writer,
            password = commentRequest.password,
            todo = todo
        )
        val saveComment = commentRepository.save(comment)
        return CommentResponse(
            id = saveComment.id!!,
            content = saveComment.content,
            writer = saveComment.writer
        )
    }

    override fun updateComment(
        todoId: Long, commentId: Long, commentRequest: CommentRequest
    ): CommentResponse {
        val comment =
            commentRepository.findByIdAndTodoId(commentId, todoId)
                ?: throw TodoNotFoundException("todo", todoId)
        if (commentRequest.password != comment.password) {
            throw IncorrectPasswordException("password", commentId)
        }
        if (commentRequest.writer != comment.writer) {
            throw WriterNotMatchedException("writer", commentId)
        } else {
            comment.content = commentRequest.content
            comment.writer = commentRequest.writer
            val updateComment = commentRepository.save(comment)

            return CommentResponse(
                id = updateComment.id!!,
                content = updateComment.content,
                writer = updateComment.writer
            )
        }
    }

    override fun deleteComment(
        todoId: Long, commentId: Long, commentDeleteRequest: CommentDeleteRequest
    ) {
        val comment = commentRepository.findByIdAndTodoId(commentId, todoId)
            ?: throw TodoNotFoundException("todo", todoId)

        if (commentDeleteRequest.password != comment.password) {
            throw IncorrectPasswordException("password", commentId)
        }
        if (commentDeleteRequest.writer != comment.writer) {
            throw WriterNotMatchedException("writer", commentId)
        }
        commentRepository.delete(comment)
    }
}