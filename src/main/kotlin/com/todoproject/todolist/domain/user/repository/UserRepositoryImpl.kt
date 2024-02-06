package com.todoproject.todolist.domain.user.repository

import com.todoproject.todolist.domain.user.model.QUser
import com.todoproject.todolist.domain.user.model.User
import com.todoproject.todolist.infra.querydsl.QueryDslSupport
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl : QueryDslSupport(), CustomUserRepository {
    private val user = QUser.user

    override fun searchNickName(nickName: String): User? {
        return queryFactory.selectFrom(user)
            .where(user.authorName.nickname.eq(nickName))
            .fetchOne()
    }
}