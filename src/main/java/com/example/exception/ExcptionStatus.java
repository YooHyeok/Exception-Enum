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

    /**
     * <pre>
     * - OCP(개발폐쇄원칙) 관점에서 권장됨. (새로운 상수 추가시 외부 코드 변경 없음.)
     *   - Enum에 상수가 추가될 경우 Enum에 속한 해당 메소드의 case 분기만 추가하면 된다.
     *     만약 외부 클래스에 catchStatus() 메소드가 구현되어 있다면 상수 추가시 변경 포인트가 외부 클래스에 발생하게 된다.
     *     어디서 수정되는가에 초점을 두어 수정 위치를 Enum 내부 한 곳에서 집중적으로 처리한다.
     * - SRP(단일책임원칙) 책임 분리 관점에서 권장하지 않음.
     *   - enum은 단순한 값 목록을 정리하는 용도이다.
     *     만약 현재 Enum클래스에서 오류에 대한 조건을 필터링 할 경우 책임이 중복되게 된다.
     * </pre>
     * @param STATUS
     * @return
     */
    public static ExcptionStatus catchStatus(ExcptionStatus STATUS) {

        /* 오류 조건 필터링 */

        return switch (STATUS) {
            case STATUS_A -> STATUS_A;
            case STATUS_B -> STATUS_B;
            case STATUS_C -> STATUS_C;
            default -> PASS;
        };
    }

}
