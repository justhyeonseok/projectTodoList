package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.DeleteCommentRequest
import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest
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
    override fun createComment(todoId: Long, createCommentRequest: CreateCommentRequest): CommentDto {
        val todo = todoRepository.findById(todoId).orElseThrow { TodoNotFoundException("todo", todoId) }
        val comment = Comment(
            content = createCommentRequest.content,
            writer = createCommentRequest.writer,
            password = createCommentRequest.password,
            todo = todo
        )
        val saveComment = commentRepository.save(comment)
        return CommentDto.from(saveComment)
    }

    @Transactional
    override fun updateComment(
        todoId: Long, commentId: Long, updateCommentRequest: UpdateCommentRequest
    ): CommentDto {
        val comment =
            commentRepository.findByIdAndTodoId(commentId, todoId)
                ?: throw TodoNotFoundException("todo", todoId)
        if (updateCommentRequest.password != comment.password) {
            throw IncorrectPasswordException("password", commentId)
        } else {
            comment.changeContent(updateCommentRequest.content)
            val updateComment = commentRepository.save(comment)

            return CommentDto.from(updateComment)
        }
    }

    @Transactional
    override fun deleteComment(
        todoId: Long, commentId: Long, deleteCommentRequest: DeleteCommentRequest
    ) {
        val comment = commentRepository.findByIdAndTodoId(commentId, todoId)
            ?: throw TodoNotFoundException("todo", todoId)

        if (deleteCommentRequest.password != comment.password) {
            throw IncorrectPasswordException("password", commentId)
        }
        if (deleteCommentRequest.writer != comment.writer) {
            throw WriterNotMatchedException("writer", commentId)
        }
        commentRepository.delete(comment)
    }
}