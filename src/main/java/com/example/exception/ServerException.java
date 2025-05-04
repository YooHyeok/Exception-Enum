package com.example.exception;

public class ServerException extends BaseException {

    public ServerException() {
        super();
    }

    ServerException(ExcptionStatus statusEnum) {
        super(statusEnum);
    }
    ServerException(ExcptionStatus statusEnum, String details) {
        super(statusEnum, details);
    }

}
