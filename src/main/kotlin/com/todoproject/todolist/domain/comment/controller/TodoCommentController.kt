package com.todoproject.todolist.domain.comment.controller

import com.todoproject.todolist.domain.comment.dto.CommentDeleteRequest
import com.todoproject.todolist.domain.comment.dto.CommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentResponse
import com.todoproject.todolist.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.io.Writer

@RequestMapping("/todos/{todoId}/comment")
@RestController
class TodoCommentController(
    private val commentService: CommentService
) {
    @PostMapping
    fun createComment(
        @PathVariable todoId: Long,
        @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, commentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentRequest: CommentRequest
    ): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, commentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @RequestBody commentDeleteRequest: CommentDeleteRequest
    ): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId, commentDeleteRequest)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}