package com.todoproject.todolist.domain.user.service

import com.todoproject.todolist.domain.user.dto.UserDto
import com.todoproject.todolist.domain.user.dto.UserSignUpRequest
import com.todoproject.todolist.domain.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(val userRepository: UserRepository): UserService {
    override fun signUpUser(userSignUpRequest: UserSignUpRequest): UserDto {
        val saveUser = userRepository.save(userSignUpRequest.to())
        return UserDto.from(saveUser)
    }



}