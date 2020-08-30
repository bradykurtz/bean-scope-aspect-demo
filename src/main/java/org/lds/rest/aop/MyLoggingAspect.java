package org.lds.rest.aop;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.aspectj.lang.reflect.MethodSignature;
import org.lds.rest.annotation.LogMe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class MyLoggingAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper objectMapper;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        Object value = joinPoint.proceed();

        long timeTaken = System.currentTimeMillis() - startTime;
        logger.info("Time Taken by {} is {}", method.getName(), timeTaken);
        return value;
    }

    @Before("@annotation(org.lds.rest.annotation.LogMe)")
    public void logMethodBefore(JoinPoint joinPoint) throws  Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameters = codeSignature.getParameterNames();
        Object[] arguments = joinPoint.getArgs();
        logData(method, parameters, arguments);

    }

    @Before("execution(* *(@org.lds.rest.annotation.LogMe (*)))")
    public void logParameterBefore(JoinPoint joinPoint) throws Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        String[] parameterNames = codeSignature.getParameterNames();
        Object[] arguments = joinPoint.getArgs();
        Map<String, Object> parameterArgumentMap = new HashMap<>();
        for(int i = 0; i < method.getParameters().length; i ++){
            Parameter currentParameter = method.getParameters()[i];
            if(currentParameter.isAnnotationPresent(LogMe.class)){
                parameterArgumentMap.put(parameterNames[i], arguments[i]);
            }
        }
        logData(method, parameterArgumentMap.keySet().toArray(new String[parameterArgumentMap.keySet().size()]), parameterArgumentMap.values().toArray(new Object[parameterArgumentMap.values().size()]));
    }

    @AfterReturning(pointcut = "execution(* *(@org.lds.rest.annotation.LogMe (*)))", returning= "result")
    public void logParameterAfter(JoinPoint joinPoint, Object result) throws  Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        logger.info(String.format("Method %s returned the result of %s", method.getName(), objectMapper.writeValueAsString(result)));
    }

    @AfterReturning(pointcut = "@annotation(org.lds.rest.annotation.LogMe)", returning= "result")
    public void logMethodAfter(JoinPoint joinPoint, Object result) throws  Throwable {
        Method method = MethodSignature.class.cast(joinPoint.getSignature()).getMethod();
        logger.info(String.format("Method %s returned the result of %s", method.getName(), objectMapper.writeValueAsString(result)));
    }

    private Map<String, Object> logData(Method method, String[] parameterNames, Object[] arguments) throws JsonProcessingException {
        Map<String, Object> myLogMap = new HashMap<>();
        logger.info(String.format("%s method called with the following values", method.getName()));
        for(int i = 0; i < arguments.length; i++){
            myLogMap.put(parameterNames[i], arguments[i]);
            logger.info(String.format("%s= %s", parameterNames[i], objectMapper.writeValueAsString(arguments[i])));
        }
        return myLogMap;
    }
}
