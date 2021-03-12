package com.huan.common.util.annotation;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.databind.PropertyName;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

import java.lang.annotation.Annotation;

/**
 * 扩展JACKSON对FastJson注释JSONField的支持
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/9
 * @since 1.0.0
 */
public class JacksonAnnotationIntrospectorEx extends JacksonAnnotationIntrospector {
    @Override
    public boolean isAnnotationBundle(Annotation ann) {
        if (ann.annotationType() == JSONField.class) {
            return true;
        }
        return super.isAnnotationBundle(ann);
    }

    @Override
    public PropertyName findNameForSerialization(Annotated a) {
        PropertyName nameForSerialization = super.findNameForSerialization(a);
        return extracted(a, nameForSerialization);
    }

    @Override
    public PropertyName findNameForDeserialization(Annotated a) {
        PropertyName nameForDeserialization = super.findNameForDeserialization(a);
        return extracted(a, nameForDeserialization);
    }

    private PropertyName extracted(Annotated a, PropertyName name) {
        if (name == null || name == PropertyName.USE_DEFAULT) {
            JSONField jsonField = _findAnnotation(a, JSONField.class);
            if (jsonField != null) {
                return PropertyName.construct(jsonField.name());
            }
        }
        return name;
    }
}
