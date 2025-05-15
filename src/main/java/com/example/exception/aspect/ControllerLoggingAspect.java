package com.example.exception.aspect;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class ControllerLoggingAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.exception.controller..*(..))")
    public void controllerMethods() {
        // 컨트롤러 패키지 내 모든 메서드
    }

    @Before("controllerMethods()")
    public void logControllerParameters(JoinPoint joinPoint) {
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        Object[] args = joinPoint.getArgs();
        StringBuilder logMessage = new StringBuilder();
        logMessage.append("[Controller] ")
                  .append(className)
                  .append(".")
                  .append(methodName)
                  .append("() - Parameters: ");

        for (Object arg : args) {
            if (arg != null && isDto(arg)) {
                logMessage.append("\n    ▶ ").append(arg.getClass().getSimpleName()).append(": ")
                          .append(toJson(arg));
            }
        }

        logger.info(logMessage.toString());
    }

    private boolean isDto(Object obj) {
        // 단순히 패키지명으로 판단하거나, @RequestBody, @ModelAttribute 어노테이션을 활용할 수도 있음
        return obj.getClass().getSimpleName().endsWith("Dto");
    }

    private String toJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(null);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            log.error("매개변수={}, Exception-Message={}", obj, e.getMessage(), e);
            return "Error converting to JSON: " + e.getMessage();
        } catch (Exception e) {
            log.error(e.getMessage());
            log.error("매개변수={}, Exception-Message={}", obj, e.getMessage(), e);
            return "Error converting to JSON: " + e.getMessage();
        }
    }
}