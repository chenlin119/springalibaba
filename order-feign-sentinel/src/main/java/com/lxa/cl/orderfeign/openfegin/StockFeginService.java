package com.lxa.cl.orderfeign.openfegin;

import com.lxa.cl.orderfeign.config.FeginConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * stock-server服务对应的fegin接口
 */

@FeignClient(value = "stock-server",path = "/stock",fallback = StockFeginImpl.class)
public interface StockFeginService {
    @GetMapping("/info")
    public String stockInfo();
}
