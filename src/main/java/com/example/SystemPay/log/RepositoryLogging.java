package com.example.SystemPay.log;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class RepositoryLogging {

    @Around("execution(* com.example.SystemPay.service..*.*(..))")
    public void LogNoReturnArg(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String name = proceedingJoinPoint.getSignature().getName();
        Object[] args = proceedingJoinPoint.getArgs();

        log.debug("Method {} with parameters {} will execute", name, Arrays.asList(args));
        Object returnedObject = proceedingJoinPoint.proceed();

        if (returnedObject == null) {
            log.debug("Method executed - {}", name);
        } else {
            log.debug("Method executed - {} with {}", name, returnedObject);
        }
    }
}
