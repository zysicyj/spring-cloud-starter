package com.huan.common.filter.xss;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Arrays;

/**
 * 请求参数处理
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/8
 * @since 1.0.0
 */
public class XssRequestWrapper extends HttpServletRequestWrapper {

    XssRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String[] getParameterValues(String parameter) {
        if (StringUtils.isBlank(parameter)) {
            return new String[]{};
        }
        //获取多个参数值的时候对所有参数值应用clean方法逐一清洁
        return Arrays.stream(super.getParameterValues(parameter)).map(this::clean).toArray(String[]::new);
    }

    @Override
    public String getHeader(String name) {
        //同样清洁请求头
        return clean(super.getHeader(name));
    }

    @Override
    public String getParameter(String parameter) {
        //获取参数单一值也要处理
        return clean(super.getParameter(parameter));
    }

    /**
     * clean方法就是对值进行HTML转义
     *
     * @param value 参数
     *
     * @return java.lang.String
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/8
     * @since 1.0.0
     */
    private String clean(String value) {
        return StringUtils.isEmpty(value) ? "" : HtmlUtils.htmlEscape(value);
    }
}    