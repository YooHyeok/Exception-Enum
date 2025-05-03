package com.example.exception;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    private StatusEnum catchStatus() {
        String statusA = "statusA";
        String statusB = "statusB";
        String statusC = "statusC";

        return switch (statusC) {
            case "statusA" -> StatusEnum.STATUS_A;
            case "statusB" -> StatusEnum.STATUS_B;
            case "statusC" -> StatusEnum.STATUS_C;
            default -> StatusEnum.PASS;
        };
    }

    public void exceptionA() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {

            statusEnum = catchStatus(); // statusEnum: PASS → STATUS_C 로 초기화됨
            if (statusEnum != StatusEnum.PASS) {
                throw new CustomException();
            }

        } catch (CustomException cae) {
            throw new CustomException(statusEnum);
        } catch (Exception e) {

        }
    }

    /**
     * [이슈 케이스] 주소값을 공유하지 못하는 경우
     * 코드 공통화를 위한 특정 로직 commonRefact() 메소드 추출 리팩토링
     * StatusEnum을 commonRefact() 메소드 안에서 값을 PASS → STATUS_C 변경.
     * 상수 특성을 지닌 Enum 변수 주소값을 통한 초기화 불가.
     * 예상: STATUS_C
     * 결과: PASS
     */
    public void exceptionB() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {
            commonRefactA(statusEnum); // StatusEnum을 메소드 안에서 값을 변경시킴. PASS → STATUS_C 로 초기화됨
            throw new CustomException();

        } catch (CustomException cae) {
            throw new CustomException(statusEnum); // 초기값을 변경할수 없으므로 초기 PASS로 찍힘
        } catch (Exception e) {

        }
    }

    /**
     *[이슈 케이스]  주소값을 공유하지 못하는 경우
     * throw 와 동시에 Exception 클래스의 생성자 매개면수로 초기화 해 준 뒤 getter로 꺼내어 다시 던진다
     */
    public void exceptionC() {
        StatusEnum statusEnum = StatusEnum.PASS;
        try {
            commonRefactB(statusEnum);

        } catch (CustomException cae) {
            throw new CustomException(cae.getStatusEnum());
        } catch (Exception e) {

        }
    }
    private void commonRefactA(StatusEnum statusEnum) {
        statusEnum = catchStatus();
    }

    private void commonRefactB(StatusEnum statusEnum) {
        statusEnum = catchStatus();
        if (statusEnum != StatusEnum.PASS) {
            throw new CustomException(statusEnum);
        }
    }


}
