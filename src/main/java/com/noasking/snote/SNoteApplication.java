package com.noasking.snote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

/**
 * 核心启动类
 */
@SpringBootApplication
@EnableCaching
public class SNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(SNoteApplication.class, args);
    }

}
