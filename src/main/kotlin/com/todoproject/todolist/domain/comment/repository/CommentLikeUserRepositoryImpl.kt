package com.todoproject.todolist.domain.comment.repository

import com.todoproject.todolist.domain.comment.model.Comment
import com.todoproject.todolist.domain.comment.model.CommentLikeUser
import com.todoproject.todolist.domain.comment.model.QCommentLikeUser
import com.todoproject.todolist.domain.user.model.User
import com.todoproject.todolist.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class CommentLikeUserRepositoryImpl : QueryDslSupport(), CustomCommentLikeUserRepository {
    private val likeUser = QCommentLikeUser.commentLikeUser
    override fun commentLikeUser(comment: Comment, user: User): CommentLikeUser? {
        return queryFactory.selectFrom(likeUser)
            .where(
                likeUser.comment.eq(comment)
                    .and(
                        likeUser.user.eq(user)
                    )
            )
            .fetchOne()
    }
}