package com.huan.common.handler;

import cn.hutool.core.io.resource.NoResourceException;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 自定义404异常
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/3/11
 * @since 1.0.0
 */
@Controller
@RequestMapping("${server.error.path:${error.path:/error}}")
public class MyErrorController {
    @RequestMapping
    public void error() throws Exception {
        throw new NoResourceException("请求资源不存在！");
    }
}