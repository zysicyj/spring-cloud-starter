package com.huan.common.util.convert;


import java.util.List;


/**
 * 自定义转换模型
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class FnConverter<T> {
    public static <A> FnConverter<A> of(Class<A> clazz) {
        return new FnConverter<>();
    }

    public String getFiledName(Converter<T> converter) {
        return Reflections.fnToFieldName(converter);
    }

    public String[] getFiledName(List<Converter<T>> list) {
        return list.stream().map(Reflections::fnToFieldName).toArray(String[]::new);
    }
}
