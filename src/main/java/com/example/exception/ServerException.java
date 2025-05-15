package com.example.exception;

public class ServerException extends BaseException {

    public ServerException() {
        super();
    }

    public ServerException(ExcptionStatus statusEnum) {
        super(statusEnum);
    }
    public ServerException(ExcptionStatus statusEnum, String details) {
        super(statusEnum, details);
    }

}
