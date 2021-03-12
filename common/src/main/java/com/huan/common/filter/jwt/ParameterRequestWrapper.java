package com.huan.common.filter.jwt;

import com.alibaba.fastjson.JSON;
import com.huan.common.util.StringJsonUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static com.huan.common.constants.Request.RequestConstants.CONTENT_TYPE;
import static org.apache.commons.lang3.StringUtils.equalsAny;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * 1. 接口请求过滤去除前后空格
 * 2. 解析jwt参数
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
@Slf4j
public class ParameterRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 这个东东就是request.getParam()的params
     */
    private final Map<String, String[]> params = new HashMap<>();


    /**
     * 解析jwt就是在这边做的，主要思想就是修改params，毕竟官方没提供setParam方法
     * 该方法还同时对参数前后空格做了处理
     *
     * @param request 请求
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    public ParameterRequestWrapper(HttpServletRequest request) {
        // 将request交给父类，以便于调用对应方法的时候，将其输出，其实父亲类的实现方式和第一种new的方式类似
        super(request);
        //将参数表，赋予给当前的Map以便于持有request中的参数
        Map<String, String[]> requestMap = new HashMap<>(request.getParameterMap());
        //这边拿到和前端约定的参数名
        String[] secretParams = requestMap.get("secretParam");
        if (secretParams == null) {
            return;
        }
        String secretParam = secretParams[0];
        //没有加密参数的话就不处理了
        if (isNotBlank(secretParam)) {
            //jwt解析一波
            Jws<Claims> jws = Jwts.parser()
                    .setSigningKey("secret".getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(secretParam);
            //这边只要过滤掉几个不需要的参数就行啦啦啦啦！！！！
            jws.getBody().forEach((x, y) -> {
                if (!equalsAny(x, "sub", "exp", "lat", "jti", "iat")) {
                    requestMap.put(x, new String[]{String.valueOf(y)});
                }
            });
        }
        //在这边全部存起来哈！！
        this.params.putAll(requestMap);
        //这个是div的，去除前后空格
        this.modifyParameterValues();
    }

    /**
     * 重写getInputStream方法  post类型的请求参数必须通过流才能获取到值
     *
     * @return javax.servlet.ServletInputStream
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    @Override
    public ServletInputStream getInputStream() throws IOException {
        //非json类型，直接返回
        if (!super.getHeader(CONTENT_TYPE).equalsIgnoreCase("json")) {
            return super.getInputStream();
        }
        //为空，直接返回
        String json = IOUtils.toString(super.getInputStream(), StandardCharsets.UTF_8);
        if (StringUtils.isEmpty(json)) {
            return super.getInputStream();
        }
        log.info("转化前参数：{}", json);
        Map<String, Object> map = StringJsonUtils.jsonStringToMap(json);
        log.info("转化后参数：{}", JSON.toJSONString(map));
        ByteArrayInputStream bis = new ByteArrayInputStream(JSON.toJSONString(map).getBytes(StandardCharsets.UTF_8));
        return new MyServletInputStream(bis);
    }

    /**
     * 将parameter的值去除空格并且空串返回 null值
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    private void modifyParameterValues() {
        Set<String> set = params.keySet();
        for (String key : set) {
            String[] values = params.get(key);
            String[] newValues = new String[values.length];
            for (int i = 0; i < values.length; i++) {
                newValues[i] = values[i].trim();
                if (newValues[i].length() <= 0) {
                    newValues[i] = null;
                }
            }
            params.put(key, newValues);
        }
    }

    /**
     * 这个吗是最主要的，去看mvn解析链的话，在{@link WebUtils#getParametersStartingWith(ServletRequest, String)}这个里面获取参数就是走的这个方法
     *
     * @return java.util.Enumeration<java.lang.String>
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(params.keySet());
        return vector.elements();
    }

    /**
     * 重写getParameter 参数从当前类中的map获取
     *
     * @return java.lang.String
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    @Override
    public String getParameter(String name) {
        String[] values = params.get(name);
        if (values == null || values.length == 0) {
            return null;
        }
        return values[0];
    }

    /**
     * 重写getParameterValues
     *
     * @return java.lang.String[]
     *
     * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
     * @date 2021/2/9
     * @since 1.0.0
     */
    @Override
    public String[] getParameterValues(String name) {//同上
        return params.get(name);
    }

    static class MyServletInputStream extends ServletInputStream {
        private final ByteArrayInputStream bis;

        public MyServletInputStream(ByteArrayInputStream bis) {
            this.bis = bis;
        }

        @Override
        public boolean isFinished() {
            return true;
        }

        @Override
        public boolean isReady() {
            return true;
        }

        @Override
        public void setReadListener(ReadListener listener) {

        }

        @Override
        public int read() {
            return bis.read();
        }
    }
}
