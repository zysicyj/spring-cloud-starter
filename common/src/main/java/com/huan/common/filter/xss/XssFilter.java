package com.huan.common.filter.xss;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 自定义过滤器
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/8
 * @since 1.0.0
 */
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class XssFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String path = ((HttpServletRequest) request).getServletPath();
        if ("/".equals(path)) {
            chain.doFilter(request, response);
        }
        if (StringUtils.containsAny(path, "/assets", "/templates", "/mapper", "/oauth")) {
            chain.doFilter(request, response);
        } else {
            chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
