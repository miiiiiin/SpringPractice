package com.demo.OrderApp.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = RuntimeException.class)
    public Response<Void> handleRuntimeException(RuntimeException e) {
        // 에러 발생 시 GlobalExceptionHandler의 handleRuntimeException이 runtimeException을 잡아서 예외처리
        return Response.fail(e.getMessage());
    }

}
