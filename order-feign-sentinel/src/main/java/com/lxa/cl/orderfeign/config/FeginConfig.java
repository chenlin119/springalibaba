package com.lxa.cl.orderfeign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//加上@Configuration是将这个fegin日志级别设置为全局，如果要单个进行配置，就将该注解去掉，然后在对应的接口上进行配置
//@Configuration
public class FeginConfig {
    @Bean
    public Logger.Level feginLevel(){
        return Logger.Level.FULL;
    }
}
