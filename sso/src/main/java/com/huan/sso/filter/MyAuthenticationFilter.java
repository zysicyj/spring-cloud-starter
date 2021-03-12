package com.huan.sso.filter;

import cn.hutool.core.date.StopWatch;
import com.huan.common.constants.Request.RequestConstants;
import com.huan.common.util.http.MultiReadHttpServletRequest;
import com.huan.common.util.http.MultiReadHttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * 访问鉴权、Token认证
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/12
 * @since 1.0.0
 */
@Slf4j
@Component
public class MyAuthenticationFilter extends OncePerRequestFilter {

    /**
     * 对请求进行过滤
     *
     * @param request     请求
     * @param response    响应
     * @param filterChain 过滤连
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/13
     * @since 1.0.0
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, @SuppressWarnings("NullableProblems") HttpServletResponse response, @SuppressWarnings("NullableProblems") FilterChain filterChain)
            throws ServletException, IOException {
        log.info("请求头类型：{}", request.getContentType());
        boolean emptyContent = request.getContentType() == null && request.getContentLength() > 0;
        boolean needContent = request.getContentType() != null && !request.getContentType().contains(RequestConstants.CONTENT_TYPE_JSON);
        if (emptyContent || needContent) {
            filterChain.doFilter(request, response);
            return;
        }

        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        MultiReadHttpServletResponse wrappedResponse = new MultiReadHttpServletResponse(response);
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            // 记录请求的消息体
            logRequestBody(wrappedRequest);
            filterChain.doFilter(wrappedRequest, wrappedResponse);
        } finally {
            stopWatch.stop();
            long usedTimes = stopWatch.getTotalTimeMillis();
            // 记录响应的消息体
            logResponseBody(wrappedRequest, wrappedResponse, usedTimes);
        }

    }

    /**
     * 记录请求体
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    private void logRequestBody(MultiReadHttpServletRequest request) {
        if (request != null) {
            try {
                String bodyJson = request.getBodyJsonStrByJson(request);
                String url = request.getRequestURI().replace("//", "/");
                log.info("-------------------------------- 请求url:  {} --------------------------------", url);
                RequestConstants.URL_MAPPING_MAP.put(url, url);
                log.info("`{}` 接收到的参数: {}", url, bodyJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 记录返回体
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/1/12
     * @since 1.0.0
     */
    private void logResponseBody(MultiReadHttpServletRequest request, MultiReadHttpServletResponse response, long useTime) {
        if (response != null) {
            byte[] buf = response.getBody();
            if (buf.length > 0) {
                String payload;
                try {
                    payload = new String(buf, 0, buf.length, response.getCharacterEncoding());
                } catch (UnsupportedEncodingException ex) {
                    payload = "[unknown]";
                }
                log.info("`{}`  耗时:{}ms  返回的参数: {}", RequestConstants.URL_MAPPING_MAP.get(request.getRequestURI()), useTime, payload);
            }
        }
    }

}