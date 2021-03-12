package com.huan.common.util.convert;


import java.io.Serializable;


/**
 * 定义函数式接口，通过该类配合Lambda及序列化获取属性
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@FunctionalInterface
public interface Converter<T> extends Serializable {
    void apply(T t);
}
