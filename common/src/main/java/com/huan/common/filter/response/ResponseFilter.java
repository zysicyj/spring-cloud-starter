package com.huan.common.filter.response;

import com.huan.common.util.result.R;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 对部分响应内容进行处理
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/24
 * @since 1.0.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ResponseFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        ResponseWrapper responseWrapper = new ResponseWrapper((HttpServletResponse) servletResponse);
        filterChain.doFilter(servletRequest, responseWrapper);
        String contentType = responseWrapper.getContentType();
        byte[] content = responseWrapper.getResponseData();
        String str;
        if (StringUtils.isNotBlank(contentType) && (contentType.contains(MediaType.APPLICATION_JSON_VALUE) || contentType.contains(MediaType.TEXT_HTML_VALUE))) {
            str = new String(content);
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            if (response.getStatus() != 200) {
                if (StringUtils.containsAny(str, "\"status\":\"false\"")) {
                    writeResponse(response, response.getStatus(), str);
                } else {
                    writeResponse(response, response.getStatus(), R.failedWithJsonData(str).toJson());
                }
            } else {
                if (StringUtils.containsAny(str, "\"status\":\"true\"")) {
                    writeResponse(response, response.getStatus(), str);
                } else {
                    writeResponse(response, response.getStatus(), R.successWithJsonData(str).toJson());
                }
            }
        }
    }

    private static void writeResponse(HttpServletResponse response, int status, String json) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(status);
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}