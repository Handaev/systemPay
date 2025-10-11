package com.example.SystemPay.log;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
public class ControllerLogging {

    @Around("")
    public void log(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

    }



}
