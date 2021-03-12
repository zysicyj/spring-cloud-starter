package com.huan.common.util.leaf;

import com.huan.common.exception.InitException;
import com.huan.common.util.leaf.common.LeafResult;
import com.huan.common.util.leaf.snowflake.SnowflakeIDGenImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SnowflakeService {

    private IDGen idGen;

    public SnowflakeService() throws InitException {
        String zkAddress = "njpkhuan.top";
        int port = Integer.parseInt("2181");
        idGen = new SnowflakeIDGenImpl(zkAddress, port);
        if (idGen.init()) {
            log.info("Snowflake Service Init Successfully");
        } else {
            throw new InitException("Snowflake Service Init Fail");
        }
    }

    public LeafResult getId(String key) {
        return idGen.get(key);
    }
}