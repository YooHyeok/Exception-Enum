package com.example.exception;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExceptionController {

    private final ExceptionService exceptionService;

    @GetMapping("/exceptionA")
    public void exceptionA() {
        exceptionService.exceptionA();
    }

    /**
     * 주소값을 공유하지 못하는 경우 - PASS로 찍힘
     */
    @GetMapping("/exceptionB")
    public void exceptionB() {
        exceptionService.exceptionB();
    }

    /**
     * 주소값을 공유하지 못하는 경우 - throw 매개면수로 초기화 해 준 뒤 getter로 꺼내어 다시 던진다
     */
    @GetMapping("/exceptionC")
    public void exceptionC() {
        exceptionService.exceptionC();
    }

    @GetMapping("/exceptionD")
    public void exceptionD() {
        exceptionService.exceptionD();
    }

}
