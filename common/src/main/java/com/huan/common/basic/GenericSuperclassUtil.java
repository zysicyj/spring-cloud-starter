package com.huan.common.basic;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericSuperclassUtil {

    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    public static Class<?> getActualTypeArgument(Class<?> clazz) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > 0) {
                entitiClass = (Class<?>) actualTypeArguments[0];
            }
        }

        return entitiClass;
    }

    /*
     * 获取泛型类Class对象，不是泛型类则返回null
     */
    static Class<?> getActualTypeArgument(Class<?> clazz, Integer pos) {
        Class<?> entitiClass = null;
        Type genericSuperclass = clazz.getGenericSuperclass();
        if (genericSuperclass instanceof ParameterizedType) {
            Type[] actualTypeArguments = ((ParameterizedType) genericSuperclass)
                    .getActualTypeArguments();
            if (actualTypeArguments != null && actualTypeArguments.length > pos) {
                entitiClass = (Class<?>) actualTypeArguments[pos];
            }
        }

        return entitiClass;
    }
}