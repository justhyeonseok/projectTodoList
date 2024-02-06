package com.todoproject.todolist.domain.user.service

import com.todoproject.todolist.domain.exception.*
import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserLoginRequest
import com.todoproject.todolist.domain.user.dto.UserLoginResponse
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest
import com.todoproject.todolist.domain.user.model.Profile
import com.todoproject.todolist.domain.user.model.User
import com.todoproject.todolist.domain.user.model.UserRole
import com.todoproject.todolist.domain.user.repository.UserRepository
import com.todoproject.todolist.infra.security.jwt.JwtPlugin
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
    val jwtPlugin: JwtPlugin
) : UserService {

    //회원가입
    @Transactional
    override fun signUpUser(userSignUpRequest: UserSignUpRequest): UserDto {
        if (userRepository.existsByUserEmail(userSignUpRequest.userEmail)) {
            throw IllegalStateException("중복된 이메일입니다.")
        }
        if (userSignUpRequest.confirmPassword != userSignUpRequest.password) {
            throw InvalidCredentialException()
        }
        if (userSignUpRequest.userRole != "MEMBER") {
            throw ModelNotFoundException("role", null)
        }

        val saveUser = userRepository.save(
            User(
                userEmail = userSignUpRequest.userEmail,
                authorName = Profile(nickname = userSignUpRequest.authorName),
                password = passwordEncoder.encode(userSignUpRequest.password),
                userRole = UserRole.MEMBER// if 문으로 멤버체크 throw IllegalArgumentException("Invalid role")
            )
        )
        return UserDto.from(saveUser)
    }

    //로그인
    @Transactional
    override fun loginUser(userLoginRequest: UserLoginRequest): UserLoginResponse {
        val user =
            userRepository.findByUserEmail(userLoginRequest.userEmail) ?: throw ModelNotFoundException("User", null)
        if (user.userRole.name != userLoginRequest.userRole || !passwordEncoder.matches(
                userLoginRequest.password,
                user.password
            )
        ) {
            throw InvalidCredentialException()
        }
        return UserLoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.userEmail,
                role = user.userRole.name
            )
        )
    }

    override fun existsByUserName(nickName: String): String {
        userRepository.searchNickName(nickName) ?: throw IsModelAvailableException(nickName)
        return "중복된 이름입니다."
    }



}