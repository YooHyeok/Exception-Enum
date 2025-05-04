package com.example.exception;

import lombok.Data;

@Data
public class BaseException extends RuntimeException {
    private String code;
    private String message;
    private ExcptionStatus statusEnum;

    public BaseException() {
        super();
    }

    public BaseException(ExcptionStatus statusEnum) {
        super(statusEnum.getMessage());
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage();
        this.statusEnum = statusEnum;
    }

    public BaseException(ExcptionStatus statusEnum, String details) {
        super(statusEnum.getMessage() + details != null && !"".equals(details) ? details : "");
        this.code = statusEnum.getCode();
        this.message = statusEnum.getMessage() + details != null && !"".equals(details) ? details : "";
        this.statusEnum = statusEnum;
    }

}
