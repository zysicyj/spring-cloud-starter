package com.huan.common.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 使用 DruidDataSource
 *
 * @author <a href = "mailto:njpkhuan@gmail.com" > huan </a >
 * @date 2021/2/1
 * @since 1.0.0
 */
@Configuration
@Slf4j
public class CustomDataSourceProxyConfig {

    @Primary
    @Bean(value = "dataSource1", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.ds0")
    public DruidDataSource druidDataSourceDs0() {
        log.debug("init dataSource : dataSource1");
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(value = "dataSourceByNacos", initMethod = "init")
    @ConfigurationProperties(prefix = "spring.datasource.druid.ds1")
    public DruidDataSource druidDataSourceDs1() {
        log.debug("init dataSource : nacos");
        return DruidDataSourceBuilder.create().build();
    }
}