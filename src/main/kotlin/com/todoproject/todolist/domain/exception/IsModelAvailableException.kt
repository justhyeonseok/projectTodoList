package com.todoproject.todolist.domain.exception

data class IsModelAvailableException(val modelName: String) : RuntimeException("$modelName 은 사용가능합니다.")