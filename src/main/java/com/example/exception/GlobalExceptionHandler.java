package com.example.exception;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    @ToString
    private static class Result {
        private String code;
        private String message;

        private static Result of(String code, String message) {
            return new Result(code, message);
        }
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<Result> handleCustomException(BaseException be) {
        Result of = Result.of(be.getCode(), be.getMessage());
        log.error("global exception debug | of: {}", of);
        return ResponseEntity.status(500).body(of);
    }
}
