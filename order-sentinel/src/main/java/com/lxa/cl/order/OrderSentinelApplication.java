package com.lxa.cl.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
//@RibbonClients(value = {@RibbonClient(name = "stock-server",configuration = RibbonConfig.class)})
public class OrderSentinelApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderSentinelApplication.class, args);
    }

}
