package com.example.exception;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Supplier;


@UtilityClass
@Slf4j
public class ExceptionMapper {

    private static void throwException(
            final ExcptionStatus status,
            final Supplier<? extends RuntimeException> supplier
    ) {
        log.debug("ExceptionMapper.throwException({}, {})", status, supplier.get().getClass().getSimpleName());
        if (status == ExcptionStatus.PASS && (supplier == null || supplier.get() == null)) return;
        throw supplier.get();
    }

    public static <T> void throwIf(final ExcptionStatus status) {

        /* 오류 조건 필터링 */

        throwException(status, () ->
            switch (status) {
                case STATUS_A, STATUS_B, STATUS_C -> new CustomException(status);
                case STATUS_D -> new ServerException(status);
                default -> null; // PASS인 경우
        });
    }
}
