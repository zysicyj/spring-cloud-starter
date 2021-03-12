package com.huan.common.aspect.version;

import com.huan.common.exception.VersionBlockedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

/**
 * 使用AOP做版本控制
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@Aspect
@Slf4j
@Component
@RefreshScope
public class VersionAspect {

    @Value("${versionEnable}")
    private String versionEnables;

    @Before(value = "pointcut()")
    public void before(JoinPoint point) {
        Object[] args = point.getArgs();
        if (args == null || args.length <= 0) {
            return;
        }
        Method method = ((MethodSignature) point.getSignature()).getMethod();
        Version version = method.getAnnotation(Version.class);
        if (version == null) {
            return;
        }
        String[] value = version.value();
        List<String> list = Arrays.stream(value).collect(Collectors.toList());
        StringTokenizer tokenizer = new StringTokenizer(versionEnables, ",");
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (list.contains(token)) {
                return;
            }
        }
        throw new VersionBlockedException("访问接口已不支持该版本！");
    }

    /**
     * 定义需要拦截的切面 —— 方法或类上包含 {@link com.huan.common.aspect.version.Version} 注解
     */
    @Pointcut(value = "@annotation(com.huan.common.aspect.version.Version)")
    public void pointcut() {
    }
}