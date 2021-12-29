package com.lxa.cl.orderfeign.intercepert;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

public class CustomFeignIntercepert implements RequestInterceptor {
    Logger logger= LoggerFactory.getLogger(this.getClass());
    public void apply(RequestTemplate template) {
        logger.info("feign拦截器");
        template.uri("/8");
    }
}
