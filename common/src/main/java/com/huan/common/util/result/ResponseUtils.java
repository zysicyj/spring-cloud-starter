package com.huan.common.util.result;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.io.IoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

/**
 * HttpServletResponse帮助类
 */
@Slf4j
public final class ResponseUtils {

    /**
     * 发送文本。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderText(HttpServletResponse response, String text) {
        render(response, "text/plain;charset=UTF-8", text);
    }

    /**
     * 发送json。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderJson(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", text);
    }


    /**
     * 发送json。使用UTF-8编码。
     * 该方法封装了失败响应
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderJsonFail(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", R.fail(text).toJson());
    }

    /**
     * 发送json。使用UTF-8编码。
     * 该方法封装了失败响应
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderJsonSuccess(HttpServletResponse response, String text) {
        render(response, "application/json;charset=UTF-8", R.success(text).toJson());
    }

    /**
     * 发送xml。使用UTF-8编码。
     *
     * @param response HttpServletResponse
     * @param text     发送的字符串
     */
    public static void renderXml(HttpServletResponse response, String text) {
        render(response, "text/xml;charset=UTF-8", text);
    }

    /**
     * 发送内容。使用UTF-8编码。
     */
    public static void render(HttpServletResponse response, String contentType, String text) {
        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        PrintWriter pWriter = null;
        try {
            pWriter = response.getWriter();
            pWriter.write(text);
            pWriter.flush();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            IoUtil.close(pWriter);
        }
    }
}