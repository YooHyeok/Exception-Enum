package com.example.exception;

public class CustomException extends BaseException {

    public CustomException() {
        super();
    }

    CustomException(ExcptionStatus statusEnum) {
        super(statusEnum);
    }
    CustomException(ExcptionStatus statusEnum, String details) {
        super(statusEnum, details);
    }

}
