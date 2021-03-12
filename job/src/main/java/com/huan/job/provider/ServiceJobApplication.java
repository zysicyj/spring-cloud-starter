package com.huan.job.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * 定时任务服务
 *
 * @author <a href="mailto:njpkhuan@gmail.com">huan</a>
 * @version 1.0.0
 * @date 2021/1/10
 */
@SpringBootApplication(scanBasePackages = {"com.huan.common", "com.huan.job.provider"})
public class ServiceJobApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServiceJobApplication.class, args);
    }
}
