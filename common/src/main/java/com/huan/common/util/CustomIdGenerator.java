package com.huan.common.util;

import com.huan.common.util.leaf.SnowFlakeSingleUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;


/**
 * 自定义id生成策略
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/3/5
 */
@Slf4j
public class CustomIdGenerator implements IdentifierGenerator {
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        return SnowFlakeSingleUtil.getInstance().getId();
    }
}
