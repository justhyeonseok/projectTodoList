package com.todoproject.todolist.domain.comment.service

import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.CommentLikeDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest
import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.model.CommentLikeUser
import com.todoproject.todolist.domain.comment.model.QCommentLikeUser.commentLikeUser
import com.todoproject.todolist.domain.comment.repository.CommentLikeUserRepository
import com.todoproject.todolist.domain.comment.repository.CommentRepository
import com.todoproject.todolist.domain.exception.ModelNotFoundException
import com.todoproject.todolist.domain.todo.repository.TodoRepository
import com.todoproject.todolist.domain.user.repository.UserRepository
import com.todoproject.todolist.infra.security.UserPrincipal
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.nio.file.attribute.UserPrincipalNotFoundException

@Service
class CommentServiceImpl(
    private val commentRepository: CommentRepository,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
    private val commentLikeUserRepository: CommentLikeUserRepository
) : CommentService {
    @Transactional
    override fun createComment(
        todoId: Long,
        createCommentRequest: CreateCommentRequest,
        user: UserPrincipal
    ): CommentDto {
        val comment = Comment(
            content = createCommentRequest.content,
            author = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id),
            todo = todoRepository.findByIdOrNull(todoId) ?: throw ModelNotFoundException("todo", todoId)
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
                ?: throw ModelNotFoundException("todo or userId", todoId)
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
                ?: throw ModelNotFoundException(
                    "todo, comment",
                    todoId
                )
        commentRepository.delete(delete)
    }

    // 인증된 유저가 댓글 하나를 좋아요를 클릭하면, false 상태를 true 로 바꾸고 comment 엔티티의 카운터를 1 증가
    // 인증된 유저가 이미 좋아요 상태라면, 카운터 1감소, 및 true 를 false 상태로 변경
    @Transactional
    override fun commentLikeStatusChange(commentId: Long, user: UserPrincipal): CommentLikeDto {
        val users = userRepository.findByIdOrNull(user.id) ?: throw ModelNotFoundException("user", user.id)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("comment", commentId)
        val commentLikeUser =
            commentLikeUserRepository.commentLikeUser(comment, users) ?: commentLikeUserRepository.save(
                CommentLikeUser(
                    comment,
                    users
                )
            )
        if (commentLikeUser.like) {
            comment.likeDown()
            commentLikeUser.likeFalse()
        } else {
            comment.likeUp()
            commentLikeUser.likeTrue()
        }
        return CommentLikeDto.status(commentLikeUser)
    }

}


