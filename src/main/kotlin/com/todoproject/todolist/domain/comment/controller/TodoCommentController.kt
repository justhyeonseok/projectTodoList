package com.todoproject.todolist.domain.comment.controller

import com.todoproject.todolist.domain.comment.dto.CreateCommentRequest
import com.todoproject.todolist.domain.comment.dto.CommentDto
import com.todoproject.todolist.domain.comment.dto.UpdateCommentRequest
import com.todoproject.todolist.domain.comment.service.CommentService
import com.todoproject.todolist.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/todos/{todoId}/comments")
@RestController
class TodoCommentController(
    private val commentService: CommentService
) {
    @PostMapping
    @Operation(summary = "댓글 생성")
    fun createComment(
        @PathVariable todoId: Long,
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody createCommentRequest: CreateCommentRequest
    ): ResponseEntity<CommentDto> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.createComment(todoId, createCommentRequest, user))
    }

    @Operation(summary = "댓글 수정")
    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal user: UserPrincipal,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentDto> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todoId, commentId, updateCommentRequest, user))
    }

    @Operation(summary = "댓글 삭제")
    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todoId: Long,
        @PathVariable commentId: Long,
        @AuthenticationPrincipal user: UserPrincipal
    ): ResponseEntity<Unit> {
        commentService.deleteComment(todoId, commentId, user)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}