package com.huan.common.util.convert;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.regex.Pattern;


/**
 * 通过反射及SerializedLambda获取指定的字段名
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
class Reflections {
    private static final Pattern GET_PATTERN = Pattern.compile("get[A-Z].*");
    private static final Pattern IS_PATTERN = Pattern.compile("is[A-Z].*");

    public static <T> String fnToFieldName(Converter<T> fn) {
        try {
            Method method = fn.getClass().getDeclaredMethod("writeReplace");
            method.setAccessible(Boolean.TRUE);
            SerializedLambda invoke = (SerializedLambda) method.invoke(fn);
            // 得到方法名
            String getter = invoke.getImplMethodName();
            // 切割得到字段名
            if (GET_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(3);
            }
            if (IS_PATTERN.matcher(getter).matches()) {
                getter = getter.substring(2);
            }
            return Introspector.decapitalize(getter);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException();
        }
    }
}