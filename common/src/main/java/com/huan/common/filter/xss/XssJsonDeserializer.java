package com.huan.common.filter.xss;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.web.util.HtmlUtils;

import java.io.IOException;

/**
 * XSS JSON序列化
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/8
 * @since 1.0.0
 */
public class XssJsonDeserializer extends JsonDeserializer<String> {
    @Override
    public String deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException {
        String value = jsonParser.getValueAsString();
        if (value != null) {
            //对于值进行HTML转义
            return HtmlUtils.htmlEscape(value);
        }
        return null;
    }

    @Override
    public Class<String> handledType() {
        return String.class;
    }
}