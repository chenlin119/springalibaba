package com.lxa.cl.order.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@Slf4j
public class OrderController {


    @RequestMapping("/getOrder")
    //@SentinelResource(value = "getOrder",blockHandler = "blockHandlergGetOrder")
    public String getOrder(){
        return "getOrder";
    }

    @RequestMapping("/getOrder1")
    //@SentinelResource(value = "getOrder",blockHandler = "blockHandlergGetOrder")
    public String getOrder1(){
        return "getOrder1";
    }

    @RequestMapping("/order")
    @SentinelResource(value = "getOrderById",blockHandler = "blockHandlergGetOrder")
    public String getOrderById(String id){
        return "正常访问";
    }

    public String blockHandlergGetOrder(BlockException e){
        //log.info(e.getMessage());
        e.printStackTrace();
        return "流控了";
    }
}
