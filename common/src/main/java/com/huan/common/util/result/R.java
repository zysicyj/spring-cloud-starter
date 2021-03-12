package com.huan.common.util.result;

import cn.hutool.json.JSONUtil;
import com.huan.common.constants.Result.ResponseCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局统一响应模板
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/9
 */
@SuppressWarnings("rawtypes")
@Data
public class R<T> implements Serializable {

    private static final long serialVersionUID = 6426193889384507066L;
    private Boolean status;

    private Integer code;

    private String message;

    private boolean jsonData = Boolean.FALSE;

    private Map<String, Object> data = new HashMap<>();

    private R() {}

    public static R success() {
        R r = new R();
        r.setStatus(true);
        r.setCode(ResponseCodeConstants.SUCCESS);
        r.setMessage("请求成功！");
        return r;
    }

    public static <T> R<T> success(String message) {
        R<T> r = new R<>();
        r.setStatus(true);
        r.setCode(ResponseCodeConstants.SUCCESS);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> fail() {
        R<T> r = new R<>();
        r.setStatus(false);
        r.setCode(ResponseCodeConstants.ERROR);
        r.setMessage("请求失败！");
        return r;
    }

    public static <T> R<T> fail(String key, Object value) {
        R<T> r = new R<>();
        r.setStatus(false);
        r.data.put(key, value);
        r.setCode(ResponseCodeConstants.ERROR);
        r.setMessage("请求失败！");
        return r;
    }

    public static <T> R<T> fail(String message) {
        R<T> r = new R<>();
        r.setStatus(false);
        r.setCode(ResponseCodeConstants.ERROR);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> fail(String message, Integer code) {
        R<T> r = new R<>();
        r.setStatus(false);
        r.setCode(code);
        r.setMessage(message);
        return r;
    }

    public static <T> R<T> successWithJsonData(T data) {
        R<T> r = new R<>();
        r.setStatus(Boolean.TRUE);
        r.setCode(ResponseCodeConstants.SUCCESS);
        r.data.put("data", data);
        r.jsonData = Boolean.TRUE;
        return r;
    }

    public static <T> R<T> successWithData(T data) {
        R<T> r = new R<>();
        r.setStatus(Boolean.TRUE);
        r.setCode(ResponseCodeConstants.SUCCESS);
        r.data.put("data", data);
        return r;
    }

    public static <T> R<T> failedWithJsonData(T data) {
        R<T> r = new R<>();
        r.setStatus(Boolean.FALSE);
        r.setCode(ResponseCodeConstants.ERROR);
        r.data.put("data", data);
        r.jsonData = Boolean.TRUE;
        return r;
    }

    public static <T> R<T> failedWithData(T data) {
        R<T> r = new R<>();
        r.setStatus(Boolean.FALSE);
        r.setCode(ResponseCodeConstants.ERROR);
        r.data.put("data", data);
        return r;
    }

    public R message(String message) {
        setMessage(message);
        return this;
    }

    public R code(Integer code) {
        setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        setData(map);
        return this;
    }

    public String toJson() {
        if (jsonData) {
            String tem = String.valueOf(data.get("data"));
            data.remove("data");
            String jsonStr = JSONUtil.toJsonPrettyStr(this);
            return StringUtils.replace(jsonStr, jsonStr.substring(30, 38), tem);
        }
        return JSONUtil.toJsonPrettyStr(this);
    }
}

