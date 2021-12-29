package com.lxa.cl.orderfeign.controller;

import com.lxa.cl.orderfeign.openfegin.ProductFeginService;
import com.lxa.cl.orderfeign.openfegin.StockFeginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    StockFeginService stockFeginService;
    //@Autowired
    //ProductFeginService productFeginService;

    @RequestMapping("/id")
    public String getOrder(String id){
        //String forObject = restTemplate.getForObject("http://stock-server/stock/info", String.class);
        String msg = stockFeginService.stockInfo();
        //String s = productFeginService.productInfo(id);
        return msg+"商品：";
    }
}
