package com.huan.common.util;

import cn.hutool.core.util.ObjectUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * json 工具
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/1/26
 * @since 1.0.0
 */
public class StringJsonUtils {
    /**
     * jsonString 转换成map，同时去除参数值空格
     *
     * @param jsonString json字符串
     *
     * @return Map
     */
    public static Map<String, Object> jsonStringToMap(String jsonString) {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = new HashMap<>();
        try {
            map = mapper.readValue(jsonString, new TypeReference<HashMap<String, Object>>() {
            });
            Set<String> set = map.keySet();
            for (String key : set) {
                if (ObjectUtil.isEmpty(map.get(key))) {
                    continue;
                }
                Object o = map.get(key);
                if (o instanceof String) {
                    String values = (String) o;
                    if (StringUtils.isNotBlank(values)) {
                        values = values.trim();
                        map.put(key, values);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
