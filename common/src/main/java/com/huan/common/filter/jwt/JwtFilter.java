package com.huan.common.filter.jwt;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 将约定的请求头参数使用jwt转化为普通参数，供系统使用
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/9
 * @since 1.0.0
 */
@Component
public class JwtFilter implements Filter {

    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        ParameterRequestWrapper paramsRequest = new ParameterRequestWrapper((HttpServletRequest) arg0);
        String[] secretParams = paramsRequest.getParameterMap().get("secretParam");
        if (secretParams == null) {
            arg2.doFilter(arg0, arg1);
        } else {
            arg2.doFilter(paramsRequest, arg1);
        }
    }

    @Override
    public void init(FilterConfig arg0) {

    }

    @Override
    public void destroy() {

    }
}
