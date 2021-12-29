package com.lxa.cl.orderfeign.openfegin;

import com.lxa.cl.orderfeign.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * stock-server服务对应的fegin接口
 */

@FeignClient(value = "stock-server",path = "stock",configuration = FeginConfig.class)
public interface StockFeginService {
    @RequestMapping("/info")
    public String stockInfo();
}
