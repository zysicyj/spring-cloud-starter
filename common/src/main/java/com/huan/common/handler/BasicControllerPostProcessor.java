package com.huan.common.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;


/**
 * 动态加载basicMapper，basicDao，basicService
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/2
 */
@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class BasicControllerPostProcessor extends CommonAnnotationBeanPostProcessor {

    private static final long serialVersionUID = -945664767382485314L;

    @SuppressWarnings("NullableProblems")
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) {
        log.debug("beanName---{}", beanName);
        String packageName = beanClass.getPackageName();
        if (StringUtils.startsWith(packageName, "com.huan") && StringUtils.endsWith(packageName, "provider.controller")) {
            try {
                updateResourceName(beanClass, beanName, "basicMapper", "Controller", "MapperImpl");
                updateResourceName(beanClass, beanName, "basicService", "Controller", "Service");
            } catch (Exception e) {
                return null;
            }
        }
        if (StringUtils.startsWith(packageName, "com.huan") && StringUtils.endsWith(packageName, "provider.service")) {
            try {
                updateResourceName(beanClass, beanName, "basicMapper", "Service", "MapperImpl");
                updateResourceName(beanClass, beanName, "basicDao", "Service", "Dao");
            } catch (Exception e) {
                return null;
            }
        }
        if (StringUtils.startsWith(packageName, "com.huan") && StringUtils.endsWith(packageName, "provider.serviceImpl")) {
            try {
                updateResourceName(beanClass, beanName, "basicMapper", "Service", "MapperImpl");
                updateResourceName(beanClass, beanName, "basicDao", "Service", "Dao");
            } catch (Exception e) {
                return null;
            }
        }
        return null;
    }

    private void updateResourceName(Class<?> beanClass, String beanName, String destField, String searchString, String replaceString)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = beanClass.getSuperclass().getDeclaredField(destField);
        Resource basicMapper = field.getAnnotation(Resource.class);
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(basicMapper);
        Field memberValues = invocationHandler.getClass().getDeclaredField("memberValues");
        memberValues.setAccessible(true);
        //noinspection unchecked
        Map<String, Object> map = (Map<String, Object>) memberValues.get(invocationHandler);
        map.put("name", StringUtils.replace(beanName, searchString, replaceString));
    }
}
