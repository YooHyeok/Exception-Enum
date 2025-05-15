package com.example.exception;

public class CustomException extends BaseException {

    public CustomException() {
        super();
    }

    public CustomException(ExcptionStatus statusEnum) {
        super(statusEnum);
    }
    public CustomException(ExcptionStatus statusEnum, String details) {
        super(statusEnum, details);
    }

}
