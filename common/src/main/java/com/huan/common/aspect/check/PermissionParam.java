package com.huan.common.aspect.check;

import java.lang.annotation.*;

/**
 * 权限校验
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @since 1.0.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface PermissionParam {
    String[] value() default "";
}
