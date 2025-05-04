package com.example.exception;

public enum ExcptionStatus {
    PASS("Suceess", "성공"),
    STATUS_A("A0001", "CustomException A 발생"),
    STATUS_B("B0001", "CustomException B 발생"),
    STATUS_C("C0001", "CustomException C 발생"),
    STATUS_D("D0001", "ServerException D 발생");

    private final String code;
    private final String message;
    ExcptionStatus(String code, String message) {
        this.code = code;
        this.message = message;

    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static ExcptionStatus catchStatus(ExcptionStatus STATUS) {
        return switch (STATUS) {
            case STATUS_A -> STATUS_A;
            case STATUS_B -> STATUS_B;
            case STATUS_C -> STATUS_C;
            default -> PASS;
        };
    }
}
