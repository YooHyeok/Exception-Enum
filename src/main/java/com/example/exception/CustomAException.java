package com.example.exception;

public class CustomAException extends BaseException {
    CustomAException(StatusEnum statusEnum) {
        super(statusEnum);
    }
    CustomAException(StatusEnum statusEnum, String details) {
        super(statusEnum, details);
    }
}
