package com.huan.common.util.leaf;

import com.huan.common.exception.InitException;
import com.huan.common.exception.LeafServerException;
import com.huan.common.util.leaf.common.LeafResult;
import com.huan.common.util.leaf.common.Status;
import lombok.extern.slf4j.Slf4j;


/**
 * 雪花算法工具
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/5
 */
@Slf4j
public class SnowFlakeSingleUtil {
    private static final String key = "spring-cloud-starter";
    private static final SnowFlakeSingleUtil instance = new SnowFlakeSingleUtil();
    private static SnowflakeService service;


    private SnowFlakeSingleUtil() {
    }

    public static SnowFlakeSingleUtil getInstance() {
        try {
            if (service == null) {
                service = new SnowflakeService();
            }
        } catch (InitException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Long getId() {
        LeafResult result;
        result = service.getId(key);
        if (result.getStatus().equals(Status.EXCEPTION)) {
            throw new LeafServerException(result.toString());
        }
        return result.getId();
    }
}
