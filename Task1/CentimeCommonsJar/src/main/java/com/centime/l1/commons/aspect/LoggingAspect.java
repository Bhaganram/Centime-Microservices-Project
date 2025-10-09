
package com.centime.l1.commons.aspect;

import java.util.Arrays;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("within(@org.springframework.stereotype.Repository *) || within(@org.springframework.stereotype.Service *) || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
    }

    @Around("(springBeanPointcut())")
    public Object logAroundServiceClass(ProceedingJoinPoint joinPoint) throws Throwable {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        String traceId = MDC.get("traceID");

        log.info("Executing: {}.{}() method with argument[s] = {} and traceId = {}", className, methodName, Arrays.toString(joinPoint.getArgs()), traceId);

        try {
            Object result = joinPoint.proceed();
            log.info("Completed execution of : {}.{}() method with argument[s] = {} and traceId = {}", className, methodName, Arrays.toString(joinPoint.getArgs()), traceId);
            return result;
        } catch (IllegalArgumentException exception) {
            log.error("Exception occurred while executing : {}.{}() method where traceId = {} and exception : {}", className, methodName, traceId, exception.getMessage());
            throw exception;
        }
    }

}
