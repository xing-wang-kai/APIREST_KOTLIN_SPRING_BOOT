package com.orgs.orgs.controller.handlers


import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ErrorHandlers {

    @ExceptionHandler(value=[IllegalArgumentException::class])
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun illegalArgumentExceptions(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exception.message!!,
            HttpStatus.INTERNAL_SERVER_ERROR.name,
            request.servletPath
        )
        return ResponseEntity.badRequest().body(errorResponse)
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleInternalServerError(request: HttpServletRequest, exception: Exception): ResponseEntity<ErrorResponse>{
        val errorResponse = ErrorResponse(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            exception.message!!,
            HttpStatus.INTERNAL_SERVER_ERROR.name,
            request.servletPath
            )
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}

data class ErrorResponse(val statusCode: Int, val message: String, val error: String, val path: String)