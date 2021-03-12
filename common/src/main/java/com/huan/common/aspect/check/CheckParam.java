package com.huan.common.aspect.check;

import javax.validation.groups.Default;
import java.lang.annotation.*;

/**
 * 在方法、类、参数上添加该注解开启校验
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CheckParam {

    Class<?>[] specialClass() default {Object.class};

    Class<?>[] value() default {Default.class};
}