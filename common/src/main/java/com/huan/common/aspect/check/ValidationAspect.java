package com.huan.common.aspect.check;

import com.huan.common.exception.CheckParamException;
import com.huan.common.exception.MyThrowable;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * 使用AOP做统一参数校验
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@Aspect
@Slf4j
@Component
public class ValidationAspect {
    @Resource
    private CheckParamUtils checkParamUtils;

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        if (args == null || args.length <= 0) {
            return;
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        CheckParam checkParam = method.getAnnotation(CheckParam.class);
        Class<?>[] classes = checkParam.value();
        for (Class<?> aClass : classes) {
            Set<ConstraintViolation<Object>> constraintViolations =
                    checkParamUtils.getValidator().forExecutables().validateParameters(point.getTarget(), method, args, aClass);
            if (constraintViolations.isEmpty()) {
                continue;
            }
            //noinspection rawtypes
            ConstraintViolation o = (ConstraintViolation) constraintViolations.toArray()[0];
            MyThrowable myThrowable = new MyThrowable(o.getMessage(), null, false, false);
            throw new CheckParamException(o.getMessage(), myThrowable, false, false,
                    constraintViolations.toArray()[0].toString(), point
            );
        }
    }

    /**
     * 定义需要拦截的切面 —— 方法或类上包含 {@link Validated} 注解
     */
    @Pointcut(value = "@annotation(com.huan.common.aspect.check.CheckParam)")
    public void pointcut() {
    }
}