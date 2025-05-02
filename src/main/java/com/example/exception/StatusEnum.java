package com.example.exception;

public enum StatusEnum {
    PASS("Suceess", "성공"),
    STATUS_A("A0001", "A 오류 발생"),
    STATUS_B("B0001", "B 오류 발생"),
    STATUS_C("C0001", "C 오류 발생");

    private final String code;
    private final String message;
    StatusEnum(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
