package com.todoproject.todolist.domain.exception

data class WriterNotMatchedException (val modelName: String, val id: Long) : RuntimeException(
    "Model $modelName not found with given id: $id"
)