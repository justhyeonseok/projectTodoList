package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CommentDeleteRequest
import com.todoproject.todolist.domain.comment.dto.CommentCreateRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.repository.CommentRepository
import com.todoproject.todolist.domain.exception.TodoNotFoundException
import com.todoproject.todolist.domain.exception.IncorrectPasswordException
import com.todoproject.todolist.domain.exception.WriterNotMatchedException
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository
) : CommentService {
    @Transactional
    override fun createComment(todoId: Long, commentRequest: CommentCreateRequest): CommentDto {
        val todo = todoRepository.findById(todoId).orElseThrow { TodoNotFoundException("todo", todoId) }
        val comment = Comment(
            content = commentRequest.content,
            writer = commentRequest.writer,
            password = commentRequest.password,
            todo = todo
        )
        val saveComment = commentRepository.save(comment)
        return CommentDto.from(saveComment)
    }

    @Transactional
    override fun updateComment(
        todoId: Long, commentId: Long, commentRequest: CommentCreateRequest
    ): CommentDto {
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

            return CommentDto.from(updateComment)
        }
    }

    @Transactional
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