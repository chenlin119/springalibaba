package com.lxa.cl.orderfeign.openfegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(value = "product-server",path = "/product")
public interface ProductFeginService {
    @RequestMapping("/{id}")
    public String productInfo(@PathVariable("id") String id);
}
