package com.example.exception;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExceptionController {

    @GetMapping("/exceptionA")
    public void exceptionA() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {

            statusEnum = catchStatus(); // PASS → STATUS_C 로 초기화됨
            if (statusEnum != StatusEnum.PASS) {
                throw new CustomAException(statusEnum);
            }

        } catch (CustomAException cae) {
            throw new CustomAException(statusEnum, cae.getMessage());
        } catch (Exception e) {

        }
    }

    /**
     * 주소값을 공유하지 못하는 경우 - PASS로 찍힘
     */
    @GetMapping("/exceptionB")
    public void exceptionB() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {
            serviceMethodA(statusEnum); // StatusEnum을 넘겨 메소드 안에서 값을 변경시킴. PASS → STATUS_C 로 초기화됨
            throw new CustomAException(statusEnum);

        } catch (CustomAException cae) {
            throw new CustomAException(statusEnum, cae.getMessage()); // 초기값을 변경할수 없으므로 초기 PASS로 찍힘
        } catch (Exception e) {

        }
    }

    /**
     * 주소값을 공유하지 못하는 경우 - throw 매개면수로 초기화 해 준 뒤 getter로 꺼내어 다시 던진다
     */
    @GetMapping("/exceptionC")
    public void exceptionC() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {
            serviceMethodB(statusEnum);

        } catch (CustomAException cae) {
            throw new CustomAException(cae.getStatusEnum(), cae.getMessage());
        } catch (Exception e) {

        }
    }
    private void serviceMethodA(StatusEnum statusEnum) {
        statusEnum = catchStatus();
    }

    private void serviceMethodB(StatusEnum statusEnum) {
        statusEnum = catchStatus();
        if (statusEnum != StatusEnum.PASS) {
            throw new CustomAException(statusEnum);
        }
    }

    private StatusEnum catchStatus() {
        String statusA = "statusA";
        String statusB = "statusB";
        String statusC = "statusC";

        return switch (statusC) {
            case "statusA" -> StatusEnum.STATUS_A;
            case "statusB" -> StatusEnum.STATUS_B;
            case "statusC" -> StatusEnum.STATUS_C;
        };
    }




}
