package com.todoproject.todolist.domain.exception

data class ExistsModelException(val modelName: String) : RuntimeException("$modelName 이 이미 사용중입니다.")