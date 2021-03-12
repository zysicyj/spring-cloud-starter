package com.huan.common.filter.xss;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * XSS Json序列化
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/8
 * @since 1.0.0
 */
public class XssJsonSerializer extends JsonSerializer<String> {
    @Override
    public Class<String> handledType() { return String.class; }

    @Override
    public void serialize(String value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (value != null) {
            //对字符串进行HTML转义
            jsonGenerator.writeString(HtmlUtils.htmlEscape(value));
        }
    }
}