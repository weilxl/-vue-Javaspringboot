package com.efficiency.exception;

import com.efficiency.common.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<Object> handleValidate(MethodArgumentNotValidException e) {
        String msg = e.getBindingResult().getFieldError() == null ? "参数校验失败"
                : e.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.fail(msg);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiResponse<Object> handleRuntime(RuntimeException e) {
        return ApiResponse.fail(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<Object> handleException(Exception e) {
        return ApiResponse.fail("系统异常：" + e.getMessage());
    }
}
