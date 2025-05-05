package com.example.exception;

import org.springframework.stereotype.Service;

@Service
public class ExceptionService {


    public void exceptionA() {
        ExcptionStatus statusEnum = ExcptionStatus.PASS;
        try {

            /* 공통화 메소드 추출될 로직 가정 */
            
            statusEnum = ExcptionStatus.catchStatus(ExcptionStatus.STATUS_A); // statusEnum: PASS → STATUS_C 로 초기화됨
            if (statusEnum != ExcptionStatus.PASS) {
                throw new CustomException();
            }

        } catch (CustomException ce) {
            throw new CustomException(statusEnum);
        } catch (ServerException se) {
            throw new ServerException(statusEnum);
        } catch (Exception e) {

        }
    }

    /**
     * [이슈 발생] 주소값을 공유하지 못하는 경우
     * 코드 공통화를 위한 특정 로직 commonRefact() 메소드 추출 리팩토링
     * StatusEnum을 commonRefact() 메소드 안에서 값을 PASS → STATUS_C 변경.
     * 상수 특성을 지닌 Enum 변수 주소값을 통한 초기화 불가. (메소드 내 지역변수로 취급됨)
     * 예상: STATUS_C
     * 결과: PASS
     */
    public void exceptionB() {
        ExcptionStatus statusEnum = ExcptionStatus.PASS;
        try {
            commonRefactA(statusEnum);
        } catch (CustomException ce) {
            throw new CustomException(statusEnum); // 초기값을 변경할수 없으므로 초기 PASS로 찍힘
        } catch (ServerException se) {
            throw new ServerException(statusEnum);
        } catch (Exception e) {

        }
    }

    /**
     *[이슈 해결] 주소값을 공유하지 못하는 경우
     * throw 와 동시에 Exception 클래스의 생성자 매개면수로 초기화 해 준 뒤 getter로 꺼내어 다시 던진다
     */
    public void exceptionC() {
        try {
            commonRefactB();
        } catch (CustomException ce) {
            throw new CustomException(ce.getStatusEnum());
        } catch (ServerException se) {
            throw new ServerException(se.getStatusEnum());
        } catch (Exception e) {

        }
    }

    public void exceptionD() {
        try {
            commonRefactD();
        } catch (CustomException ce) {
            throw new CustomException(ce.getStatusEnum());
        } catch (ServerException se) {
            throw new ServerException(se.getStatusEnum());
        } catch (Exception e) {

        }
    }
    private void commonRefactA(ExcptionStatus statusEnum) {

        /* 공통화 메소드 추출된 로직 가정 */
        
        statusEnum = ExcptionStatus.catchStatus(ExcptionStatus.STATUS_B); // PASS → STATUS_B로 변경 (지역변수로 취급됨.)
        if (statusEnum != ExcptionStatus.PASS) {
            throw new CustomException();
        }
    }

    private void commonRefactB() {

        /* 공통화 메소드 추출된 로직 가정 */
        
        ExcptionStatus statusEnum = ExcptionStatus.catchStatus(ExcptionStatus.STATUS_C);
        if (statusEnum != ExcptionStatus.PASS) {
            throw new CustomException(statusEnum);
        }
    }

    private void commonRefactD() {

        /* 공통화 메소드 추출된 로직 가정 */

        ExceptionMapper.throwIf(ExcptionStatus.PASS);
    }


}
